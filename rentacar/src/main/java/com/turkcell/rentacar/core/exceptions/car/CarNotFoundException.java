package com.turkcell.rentacar.core.exceptions.car;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class CarNotFoundException extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	
    public CarNotFoundException(String message) {
    	super(message);
    }
}
