package com.turkcell.rentacar.business.dtos;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentDto {
	
	private int rentId;
	private LocalDate startDate;
	private LocalDate returnDate;
	private int carId;

}
