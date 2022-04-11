package com.turkcell.rentacar.business.dtos.orderedService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderedServiceDto {

	private int id;
	private int orderedServiceAmount;
	private String additionalServiceName;
	private double additionalServiceDailyPrice;
	private int rentRentId;
}
