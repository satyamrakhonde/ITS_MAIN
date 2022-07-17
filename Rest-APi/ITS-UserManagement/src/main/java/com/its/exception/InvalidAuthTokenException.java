package com.its.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAuthTokenException extends RuntimeException {
	
	private String message;

	public InvalidAuthTokenException(String message) {
		this.message = message;
	}

	public InvalidAuthTokenException() {
	}

	@Override
	public String toString() {
		return "Invalid Auth Token Exception: "+this.message;
	}
	
	

}
