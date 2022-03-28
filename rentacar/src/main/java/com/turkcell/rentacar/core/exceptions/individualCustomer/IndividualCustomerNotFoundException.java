package com.turkcell.rentacar.core.exceptions.individualCustomer;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class IndividualCustomerNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public IndividualCustomerNotFoundException(String message) {
		super(message);
	}
}
