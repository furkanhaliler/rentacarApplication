package com.turkcell.rentacar.core.exceptions.orderedService;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class OrderedServiceNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public OrderedServiceNotFoundException(String message) {
		super(message);
	}
}
