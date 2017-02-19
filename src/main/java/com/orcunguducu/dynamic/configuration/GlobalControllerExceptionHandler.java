package com.orcunguducu.dynamic.configuration;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler{
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = LoggerFactory
			.getLogger(GlobalControllerExceptionHandler.class);
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity uniqueValueException(ConstraintViolationException e) {
		LOG.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}
	
	@ExceptionHandler(value = {Exception.class})
    public ResponseEntity defaultExceptionHandler(HttpServletRequest request, Exception e) {
		LOG.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
