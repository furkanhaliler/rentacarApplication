package com.turkcell.rentacar.core.exceptions.rent;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class CarIsCurrentlyRentedException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public CarIsCurrentlyRentedException(String message) {
		super(message);
	}
}
