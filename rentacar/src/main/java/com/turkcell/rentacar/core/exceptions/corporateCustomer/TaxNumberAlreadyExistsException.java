package com.turkcell.rentacar.core.exceptions.corporateCustomer;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class TaxNumberAlreadyExistsException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public TaxNumberAlreadyExistsException(String message) {
		super(message);
	}
}
