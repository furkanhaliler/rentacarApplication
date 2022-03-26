package com.turkcell.rentacar.business.requests.CarMaintenance;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCarMaintenanceRequest {

	@NotNull
	@Min(1)
	private int maintenanceId;
	
	@NotNull
	@Size(min = 2, max = 50)
	private String maintenanceDescription;
	
	private LocalDate returnDate;
	
	@NotNull
	@Min(1)
    private int carId;
}
