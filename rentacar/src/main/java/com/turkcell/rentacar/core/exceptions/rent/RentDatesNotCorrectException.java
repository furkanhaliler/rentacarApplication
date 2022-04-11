package com.turkcell.rentacar.core.exceptions.rent;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class RentDatesNotCorrectException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public RentDatesNotCorrectException(String message) {
		super(message);
	}
}
