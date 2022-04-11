package com.turkcell.rentacar.business.requests.carMaintenance;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarMaintenanceRequest {

	@NotNull
	@Min(value = 1, message = ValidationMessages.CAR_MAINTENANCE_ID_RULE)
	private int maintenanceId;
}
