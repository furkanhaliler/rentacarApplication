package com.turkcell.rentacar.core.exceptions.user;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class UserNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
