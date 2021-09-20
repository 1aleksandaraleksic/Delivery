package com.pgciric.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pgciric.exception.NotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<MessageResponse> hendleExceptio(Exception e) {
		MessageResponse error = new MessageResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<MessageResponse> hendleExceptio(NotFoundException e) {
		MessageResponse error = new MessageResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}
