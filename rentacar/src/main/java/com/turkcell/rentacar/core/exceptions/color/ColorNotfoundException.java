package com.turkcell.rentacar.core.exceptions.color;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class ColorNotfoundException extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	
    public ColorNotfoundException(String message) {
    	 super(message);
    }
}
