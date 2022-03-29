package com.turkcell.rentacar.business.requests.carMaintenance;

import java.time.LocalDate;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarMaintenanceRequest {

	@NotNull
	@Size(min = 2, max = 50)
	private String maintenanceDescription;
	
	private LocalDate returnDate;
	
	@NotNull
	@Min(1)
	private int carId;
	
}
