package com.fitting.eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fitting.eureka.EurekaApplicationTests.Application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT, properties = { "spring.jmx.enabled=true",
		"management.security.enabled=false", "management.endpoints.web.exposure.include=*" })
class EurekaApplicationTests {
	
	@LocalServerPort
	private int port = 0;
	
	@Test
	void noDoubleSlashes() {
		String basePath = "http://localhost:" + this.port + "/";
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity(basePath, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		String body = entity.getBody();
		assertThat(body).isNotNull();
		assertThat(body.contains(basePath + "/")).as("basePath contains double slashes").isFalse();
	}
	
	@Configuration(proxyBeanMethods = false)
	@EnableAutoConfiguration
	@EnableEurekaServer
	protected static class Application {

	}
}
