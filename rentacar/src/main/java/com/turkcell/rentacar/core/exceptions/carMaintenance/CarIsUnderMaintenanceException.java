package com.turkcell.rentacar.core.exceptions.carMaintenance;

import com.turkcell.rentacar.core.exceptions.BusinessException;

public class CarIsUnderMaintenanceException extends BusinessException{

	private static final long serialVersionUID = 1L;

	public CarIsUnderMaintenanceException(String message) {
		super(message);
	}
}
