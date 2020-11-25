package com.mrpowers;

public class IllegalMoveException extends java.lang.Exception {

	public IllegalMoveException() {
	}
	
	public IllegalMoveException(String message) {
	    super(message);
	}
	
	public IllegalMoveException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IllegalMoveException(Throwable cause) {
		super(cause);
	}
	
	private static final long serialVersionUID = 1L;

}
