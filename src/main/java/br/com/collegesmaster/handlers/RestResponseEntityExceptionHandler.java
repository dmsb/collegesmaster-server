package br.com.collegesmaster.handlers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {
	
	private static final Logger LOGGER = LogManager.getLogger(RestResponseEntityExceptionHandler.class);
	
	@ExceptionHandler(value = { ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintValidationException(ConstraintViolationException ex, WebRequest request) {
        List<String> bodyOfResponse = ex.getConstraintViolations()
        			.stream()
        			.map(violation -> violation.getMessage())
        			.collect(Collectors.toList());
        
        LOGGER.error(ex.getMessage());
        
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}