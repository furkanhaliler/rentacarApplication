package com.turkcell.rentacar.core.exceptions.rent;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class RentReturnDateDelayedException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public RentReturnDateDelayedException(String message) {
		super(message);
	}
}
