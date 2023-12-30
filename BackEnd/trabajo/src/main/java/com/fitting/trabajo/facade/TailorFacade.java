package com.fitting.trabajo.facade;

import java.net.URI;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fitting.trabajo.model.pojo.Tailor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TailorFacade {

	@Value("${tailor.getOne}")
	private String getTailorGetOneUrl;
	
	private final RestTemplate restTemplate;
	
	/**
	 * get a tailor
	 * @param idTailor
	 * @return if it finds a tailor updated else returns null
	 */
	public Tailor getTailor(String idTailor) {
		try {
			return restTemplate.getForObject(String.format(getTailorGetOneUrl, idTailor), Tailor.class);
		} catch (HttpClientErrorException e) {
			log.error("Client Error: {}, Tailor with ID {} not found", e.getStatusCode(), idTailor);
			return null;
		}
	}
	/**
	 * Update a tailor with a new availability list
	 * @param idTailor
	 * @param new availabilities
	 * @return if it finds a tailor updated else returns null
	 */
	public Tailor updateATailor(String idTailor, Map<String, Object> params) {
		try { 
						
		    HttpClient httpClient = HttpClientBuilder.create().build();
		    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		    restTemplate.setRequestFactory(requestFactory);
			
			return restTemplate.patchForObject(URI.create(String.format(getTailorGetOneUrl, idTailor)), params, Tailor.class);
		} catch (HttpClientErrorException e) {
			log.error("Client Error: {}, Tailor with ID {} not found", e.getStatusCode(), idTailor);
			return null;
		}
	}
}
