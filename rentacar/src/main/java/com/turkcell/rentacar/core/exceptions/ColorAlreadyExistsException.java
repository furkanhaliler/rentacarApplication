package com.turkcell.rentacar.core.exceptions;

public class ColorAlreadyExistsException extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	
    public ColorAlreadyExistsException(String message) {
    	super(message);
    }
}
