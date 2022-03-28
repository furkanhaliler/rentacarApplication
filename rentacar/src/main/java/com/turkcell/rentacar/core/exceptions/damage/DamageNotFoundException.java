package com.turkcell.rentacar.core.exceptions.damage;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class DamageNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public DamageNotFoundException(String message) {
		super(message);
	}
}
