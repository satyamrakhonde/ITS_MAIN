package com.zensar.actuator;

import java.util.Arrays;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id="bug-fixes")
public class CustomHrActuator {
	private Map<String, List<String>> customAdvstats=new HashMap<>();
	
	@PostConstruct
	public void init() {
		customAdvstats.put("v1", Arrays.asList("Hr status issue", "Status can not be shown"));
		customAdvstats.put("v2", Arrays.asList("Hr rating issue", "Rating is not available"));
	}
	
	@ReadOperation
	public Map<String, List<String>> CustomAdvertiseActuator(){
		return this.customAdvstats;
	}
}
	


