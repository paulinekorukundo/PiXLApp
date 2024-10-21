package com.PiXl.mainframe.Exceptions;

import java.util.NoSuchElementException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PixlExceptions {
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchPokemonExists(NoSuchElementException ex){
		return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(ex.getMessage());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
	    return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(ex.getMessage());
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<String> handleNoResourceFoundException(NoResourceFoundException ex){
	    return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Endpoint does not exist!");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllOtherExceptions(Exception ex){
	    return ResponseEntity.internalServerError().body("Unexpected error encountered!");
	}
}
