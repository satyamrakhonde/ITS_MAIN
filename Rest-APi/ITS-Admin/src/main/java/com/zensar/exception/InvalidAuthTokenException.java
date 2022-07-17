package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAuthTokenException extends RuntimeException {
    String msg;

    public InvalidAuthTokenException() {
	// TODO Auto-generated constructor stub
	this.msg = "";
    }

    public InvalidAuthTokenException(String msg) {
	this.msg = msg;
    }

    @Override
    public String toString() {
	return " [msg=" + msg + "]";
    }

}