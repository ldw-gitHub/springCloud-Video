package com.itcast.dw.test.exception;

public class CacheLockException extends RuntimeException{

	private static final long serialVersionUID = -427284358816288107L;
	
	private String message;
    
    public CacheLockException(String message) {
    	super(message);
    	this.message=message;
    }


}
