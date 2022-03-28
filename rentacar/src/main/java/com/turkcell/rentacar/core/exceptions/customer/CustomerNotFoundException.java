package com.turkcell.rentacar.core.exceptions.customer;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class CustomerNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
