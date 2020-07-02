package com.vw.example.fulfillment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import java.time.Instant;

@Slf4j
@SpringBootApplication
public class FulfillmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FulfillmentApplication.class, args);
	}
}

@Slf4j
@Controller
class RSocketHandler{

	@MessageMapping("fulfillment-order")
	public Mono<FulfillmentStatus> currentMarketData(String orderId) {
		log.info("Received RSocket request: {}", orderId);
		return Mono.just(
				(Math.random() >= .5)
						? ((Math.random() >= .5)
							? new FulfillmentStatus(orderId, "COMPLETED", Instant.now().getEpochSecond())
							: new FulfillmentStatus(orderId, "SHIPPED", Instant.now().getEpochSecond())
							)
						: ((Math.random() >= .5)
							? new FulfillmentStatus(orderId, "PENDING", Instant.now().getEpochSecond())
							: new FulfillmentStatus(orderId, "AWAITING PAYMENT", Instant.now().getEpochSecond())
							)
				);
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