package com.zensar.exception;

public class InvalidIdException extends RuntimeException{
	
	
	
	private String message;

	public InvalidIdException(String message) {
		this.message = message;
	}

	public InvalidIdException() {
		this.message="";
	}

	@Override
	public String toString() {
		return "Invalid authtoken Exception: "+this.message;
	}

}
