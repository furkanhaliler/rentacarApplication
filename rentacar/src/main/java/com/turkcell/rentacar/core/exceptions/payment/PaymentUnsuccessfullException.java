package com.turkcell.rentacar.core.exceptions.payment;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class PaymentUnsuccessfullException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public PaymentUnsuccessfullException(String message) {
		super(message);
	}
}
