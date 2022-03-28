package com.turkcell.rentacar.core.exceptions.creditCard;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class CreditCardNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public CreditCardNotFoundException(String message) {
		super(message);
	}
}
