package com.turkcell.rentacar.business.dtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedServiceListDto {

	private int id;
	private int orderedServiceAmount;
	private String additionalServiceName;
	private double additionalServiceDailyPrice;
	private int rentRentId;
}
