package com.fitting.trabajo.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsConfig {
    @Bean
    WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000/")
				//.allowedOrigins("*")
				//.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
				.allowedMethods("*")
				.allowedHeaders("*")
				.allowCredentials(true);
			}
		};
	}
}
