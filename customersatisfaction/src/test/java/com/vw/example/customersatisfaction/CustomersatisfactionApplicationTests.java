package com.vw.example.customersatisfaction;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = "management.metrics.export.wavefront.enabled=false")
@SpringBootTest
class CustomersatisfactionApplicationTests {

	@Test
	void contextLoads() {
	}

}
