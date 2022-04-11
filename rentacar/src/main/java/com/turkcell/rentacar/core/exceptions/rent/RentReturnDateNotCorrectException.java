package com.turkcell.rentacar.core.exceptions.rent;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class RentReturnDateNotCorrectException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public RentReturnDateNotCorrectException(String message) {
		super(message);
	}
}
