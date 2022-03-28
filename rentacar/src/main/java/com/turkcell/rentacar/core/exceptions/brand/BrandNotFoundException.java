package com.turkcell.rentacar.core.exceptions.brand;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class BrandNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public BrandNotFoundException(String message) {
    	super(message);
    }
}
