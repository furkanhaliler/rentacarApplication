package com.turkcell.rentacar.core.exceptions.city;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class CityNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public CityNotFoundException(String message) {
		super(message);
	}
}
