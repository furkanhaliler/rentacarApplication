package com.turkcell.rentacar.business.dtos.additionalService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalServiceListDto {

	private int id;
	private String additionalServiceName;
	private double dailyPrice;
}
