package com.turkcell.rentacar.business.dtos.rent;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentDTO {

	private int rentId;
	private LocalDate rentStartDate;
	private LocalDate rentReturnDate;
}
