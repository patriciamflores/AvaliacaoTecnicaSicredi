package com.patricia.votingmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.patricia.votingmanagement.exception.InvalidRequestException;
import com.patricia.votingmanagement.exception.NotAuthorizedException;
import com.patricia.votingmanagement.exception.NotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(InvalidRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleException(InvalidRequestException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFoundException(NotFoundException exception) {
		return exception.getMessage();
	}
	
	@ExceptionHandler(InterruptedException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String timerTaskInterruptedException(InterruptedException exception) {
		return "Error during vote session. Session aborted.";
	}
	
	
	@ExceptionHandler(NotAuthorizedException.class)
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public String timerTaskInterruptedException(NotAuthorizedException exception) {
		return exception.getMessage();
	}
}
