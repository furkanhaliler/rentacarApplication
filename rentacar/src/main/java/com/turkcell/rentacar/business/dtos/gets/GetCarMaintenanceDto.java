package com.turkcell.rentacar.business.dtos.gets;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarMaintenanceDto {

	private int maintenanceId;
	private String maintenanceDescription;
	private LocalDate returnDate;
	private int carId;
}
