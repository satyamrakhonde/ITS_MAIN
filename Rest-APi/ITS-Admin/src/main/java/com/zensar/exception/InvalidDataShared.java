package com.zensar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDataShared extends RuntimeException {
    String msg;

    public InvalidDataShared() {
	// TODO Auto-generated constructor stub
	this.msg = "";
    }

    public InvalidDataShared(String msg) {
	this.msg = msg;
    }

    @Override
    public String toString() {
	return "InvalidDataShared [msg=" + msg + "]";
    }

}