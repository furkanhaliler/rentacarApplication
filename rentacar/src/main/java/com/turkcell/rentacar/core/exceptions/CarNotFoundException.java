package com.turkcell.rentacar.core.exceptions;

public class CarNotFoundException extends BusinessException {
    public CarNotFoundException(String message) {
    	super(message);
    }
}
