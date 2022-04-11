package com.turkcell.rentacar.business.requests.carMaintenance;

import java.time.LocalDate;

import javax.validation.constraints.*;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

	@NotNull
	@Size(min = 2, max = 50, message = ValidationMessages.CAR_MAINTENANCE_DESCRIPTION_RULE)
	private String maintenanceDescription;
	
	private LocalDate returnDate;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.CAR_MAINTENANCE_ID_RULE)
	private int carId;
	
}
