package com.portal.springbootapp.exception;

public class BookNotFoundException extends RuntimeException {
	
	 public BookNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }

}
