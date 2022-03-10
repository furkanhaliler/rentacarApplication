package com.turkcell.rentacar.core.exceptions;

public class BrandAlreadyExistsException extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	
    public BrandAlreadyExistsException(String message) {
    	super(message);
    }
}
