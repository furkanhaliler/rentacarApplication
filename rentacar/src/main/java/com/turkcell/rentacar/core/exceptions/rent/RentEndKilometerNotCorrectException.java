package com.turkcell.rentacar.core.exceptions.rent;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class RentEndKilometerNotCorrectException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public RentEndKilometerNotCorrectException(String message) {
		super(message);
	}
}
