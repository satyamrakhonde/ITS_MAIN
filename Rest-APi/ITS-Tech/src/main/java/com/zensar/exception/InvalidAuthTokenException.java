package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
	
	
	
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAuthTokenException extends RuntimeException{

	
	private String message;	
	
	public InvalidAuthTokenException(String message) {
		this.message = message;
	}
		
	public InvalidAuthTokenException() {
		this.message="";
	}
	
		
	@Override
	public String toString() {
		return "Invalid authtoken Exception: "+this.message;
	}
}