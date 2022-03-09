package com.turkcell.rentacar.business.dtos;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarMaintenanceDto {

	private int maintenanceId;
	private String maintenanceDescription;
	private LocalDate startDate;
	private LocalDate returnDate;
	private int carId;
}
