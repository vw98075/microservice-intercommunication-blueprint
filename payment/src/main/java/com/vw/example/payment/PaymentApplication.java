package com.vw.example.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@EnableBinding(Source.class)
@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class PaymentApplication {

	private final ObjectMapper objectMapper;
	private Mono<RSocketRequester> requesterMono;

	@SneakyThrows
	private String json(Object o){
		return this.objectMapper.writeValueAsString(o);
	}

	@Bean
	WebClient httpClient(WebClient.Builder builder){
		return builder.build();
	}

	@Bean
	public Mono<RSocketRequester> myRequester(RSocketRequester.Builder rsocketRequesterBuilder, RSocketStrategies strategies) {

		this.requesterMono = rsocketRequesterBuilder
							.rsocketStrategies(strategies)
							.connectTcp("localhost", 7000);
		return this.requesterMono;
	}

	@Bean
	RouterFunction<ServerResponse> httpEndPoint(Source customerSatisfactionService){

		return route()
				.GET("/payments", request -> {

					String orderId = request.queryParam("orderId").get();
					Mono<FulfillmentStatus> fulfillmentMono =
							this.requesterMono.flatMap(
									requester -> requester.route("fulfillment-order")
													.data(orderId)
													.retrieveMono(FulfillmentStatus.class)
							);

					Map<String, String> payload = Collections.singletonMap("orderId", orderId);
					String payloadString = json(payload);

					customerSatisfactionService
							.output()
							.send(MessageBuilder.withPayload(payloadString).build());

					return (Math.random() >= .9) ?
							ServerResponse.badRequest().build() :
							ServerResponse.ok().body(fulfillmentMono, FulfillmentStatus.class);
				})
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class FulfillmentStatus{
	private String orderId;
	private String status;
	private Long updatedTime;
}