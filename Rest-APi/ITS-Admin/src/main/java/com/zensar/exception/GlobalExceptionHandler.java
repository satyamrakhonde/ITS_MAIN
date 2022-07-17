package com.zensar.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = InvalidIdException.class)
	public ResponseEntity<Object> handleInvalidCredentials(RuntimeException exception, WebRequest request) {
		String errorMsg = "{\"error\": \"Invalid  id \"}";

		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMsg, new HttpHeaders(),
				HttpStatus.CONFLICT, request);
		return response;

	}

// InvalidAuthTokenException
	@ExceptionHandler(value = InvalidAuthTokenException.class)
	public ResponseEntity<Object> handleInvalidTokens1(RuntimeException exception, WebRequest request) {
		String errorMsg = "{\"error\": \"Invalid auth token \"}";

		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMsg, new HttpHeaders(),
				HttpStatus.CONFLICT, request);
		return response;

	}
	
	@ExceptionHandler(value = InvalidDataShared.class)
	public ResponseEntity<Object> handleInvalidTokens(RuntimeException exception, WebRequest request) {
		String errorMsg = "{\"error\": \"Invalid data to share \"}";

		ResponseEntity<Object> response = handleExceptionInternal(exception, errorMsg, new HttpHeaders(),
				HttpStatus.CONFLICT, request);
		return response;

	

}
	
}