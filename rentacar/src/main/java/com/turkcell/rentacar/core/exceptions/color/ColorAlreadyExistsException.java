package com.turkcell.rentacar.core.exceptions.color;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class ColorAlreadyExistsException extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	
    public ColorAlreadyExistsException(String message) {
    	super(message);
    }
}
