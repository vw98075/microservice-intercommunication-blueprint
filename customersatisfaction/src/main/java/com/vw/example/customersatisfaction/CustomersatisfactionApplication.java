package com.vw.example.customersatisfaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
@SpringBootApplication
@Slf4j
public class CustomersatisfactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomersatisfactionApplication.class, args);
	}

	@StreamListener(Sink.INPUT)
	public void consumeNewMessage(String msg) {
		log.info("Received Kafka message: {}", msg);
	}
}
