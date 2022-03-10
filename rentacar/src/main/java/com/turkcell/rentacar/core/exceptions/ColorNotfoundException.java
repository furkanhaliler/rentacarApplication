package com.turkcell.rentacar.core.exceptions;

public class ColorNotfoundException extends BusinessException {
	
	private static final long serialVersionUID = 1L;
	
    public ColorNotfoundException(String message) {
    	 super(message);
    }
}
