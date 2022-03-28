package com.turkcell.rentacar.core.exceptions.invoice;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class InvoiceNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public InvoiceNotFoundException(String message) {
		super(message);
	}
}
