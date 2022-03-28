package com.turkcell.rentacar.core.exceptions.additionalService;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class AdditionalServiceNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public AdditionalServiceNotFoundException(String message) {
		super(message);
	}
}
