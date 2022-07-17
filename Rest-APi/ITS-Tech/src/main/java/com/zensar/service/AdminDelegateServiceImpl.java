package com.zensar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdminDelegateServiceImpl implements AdminServiceDelegate {
	
	@Autowired
	RestTemplate restTemplate;

	@Override
	public boolean isDeleteSuccessful(int id) {
		HttpEntity<Integer> entity = new HttpEntity<>(id);

		ResponseEntity<Boolean> response=this.restTemplate.exchange("http://API-GATEWAY/admin/panel/tech/{id}", HttpMethod.DELETE, entity, Boolean.class,id);
		return response.getBody();
	}
	
	

}
