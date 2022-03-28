package com.turkcell.rentacar.core.exceptions.rent;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class RentNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public RentNotFoundException(String message) {
		super(message);
	}
}
