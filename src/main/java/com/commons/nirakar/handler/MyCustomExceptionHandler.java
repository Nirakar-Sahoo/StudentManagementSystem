package com.commons.nirakar.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyCustomExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e){
		ResponseEntity<String> enity=new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		return enity;
	} 
	
}
