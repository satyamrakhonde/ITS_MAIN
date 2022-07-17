package com.zensar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserServiceDelegateImpl implements UserServiceDelegate {

	@Autowired
	RestTemplate restTemplate;

@CircuitBreaker(name = "AUTH_TOKEN_VALIDATION", fallbackMethod = "fallbackIsTokenValid")
	@Override
	public boolean isTokenValid(String authToken) {
//		String url = "http://localhost:8003/admin/validate";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", authToken);
		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<Boolean> response = this.restTemplate.exchange("http://API-GATEWAY/its-login/token/admin/validate",
				HttpMethod.GET, entity, Boolean.class);
		return response.getBody();
	}

public boolean fallbackIsTokenValid(String authToken, Exception exception) {
System.out.println("ITS login failed - inside fallbackistokenvalid()" + exception.toString());
return false;
}

}