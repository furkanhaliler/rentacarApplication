package com.turkcell.rentacar.core.exceptions.individualCustomer;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class NationalIdentityNumberAlreadyExistsException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public NationalIdentityNumberAlreadyExistsException(String message) {
		super(message);
	}
}
