package com.turkcell.rentacar.business.dtos.lists;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarMaintenanceListDto {

	private int maintenanceId;
	private String maintenanceDescription;
	private LocalDate startDate;
	private LocalDate returnDate;
	private int carId;
	
}