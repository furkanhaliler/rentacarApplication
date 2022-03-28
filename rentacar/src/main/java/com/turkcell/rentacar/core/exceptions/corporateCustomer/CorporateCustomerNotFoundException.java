package com.turkcell.rentacar.core.exceptions.corporateCustomer;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class CorporateCustomerNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public CorporateCustomerNotFoundException(String message) {
		super(message);
	}
}
