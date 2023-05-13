package com.banks.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException exception){
		
		
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	
	
}
