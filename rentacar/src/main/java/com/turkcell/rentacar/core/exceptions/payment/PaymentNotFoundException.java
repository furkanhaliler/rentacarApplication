package com.turkcell.rentacar.core.exceptions.payment;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class PaymentNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public PaymentNotFoundException(String message) {
		super(message);
	}
}
