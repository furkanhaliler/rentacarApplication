package com.turkcell.rentacar.core.exceptions;

public class BrandNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public BrandNotFoundException(String message) {
    	super(message);
    }
}
