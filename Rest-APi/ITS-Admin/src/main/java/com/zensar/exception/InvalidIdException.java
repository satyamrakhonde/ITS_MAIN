package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidIdException extends RuntimeException {
    String msg;

    public InvalidIdException() {
	// TODO Auto-generated constructor stub
	this.msg = "";
    }

    public InvalidIdException(String msg) {
	this.msg = msg;
    }

    @Override
    public String toString() {
	return " [msg=" + msg + "]";
    }

}