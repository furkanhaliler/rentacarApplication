package com.turkcell.rentacar.business.dtos.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

	private int id;
	private double dailyPrice;
	private boolean rentStatus;


}
