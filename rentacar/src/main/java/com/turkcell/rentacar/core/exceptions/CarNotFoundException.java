package com.turkcell.rentacar.core.exceptions;

public class CarNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	
    public CarNotFoundException(String message) {
    	super(message);
    }
}