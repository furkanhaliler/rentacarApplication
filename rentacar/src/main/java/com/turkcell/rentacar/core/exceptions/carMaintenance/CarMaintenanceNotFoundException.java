package com.turkcell.rentacar.core.exceptions.carMaintenance;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class CarMaintenanceNotFoundException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public CarMaintenanceNotFoundException(String message) {
		super(message);
	}
}
