package com.vw.example.gateway;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class GatewayApplication {

	@Bean
	RouteLocator gateway(RouteLocatorBuilder rlb){
		return rlb
				.routes()
				.route(rs -> rs
						.path("/orders")
						.filters(fs -> fs
								.setPath("/payments")
								.addRequestParameter("orderId", UUID.randomUUID().toString())
						)
						.uri("http://localhost:8092"))
				.build();
	}

	@Bean
	public Sampler defaultSampler()    {
		return Sampler.ALWAYS_SAMPLE;
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
