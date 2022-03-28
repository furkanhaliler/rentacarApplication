package com.turkcell.rentacar.core.exceptions.additionalService;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class AdditionalServiceNameAlreadyExistsException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public AdditionalServiceNameAlreadyExistsException(String message) {
		super(message);
	}
}
