package com.turkcell.rentacar.core.exceptions.city;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class CityNameAlreadyExistsException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public CityNameAlreadyExistsException(String message) {
		super(message);
	}
}
