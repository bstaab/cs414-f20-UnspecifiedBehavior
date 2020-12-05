package com.mrpowers.exceptions;

public class IllegalPositionException extends java.lang.Exception {

	public IllegalPositionException() {
	}
	
	public IllegalPositionException(String message) {
	    super(message);
	}
	
	public IllegalPositionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalPositionException(Throwable cause) {
		super(cause);
	}
	
	private static final long serialVersionUID = 1L;

}
