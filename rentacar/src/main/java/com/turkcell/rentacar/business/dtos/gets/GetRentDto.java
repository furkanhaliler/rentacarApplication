package com.turkcell.rentacar.business.dtos.gets;

import java.time.LocalDate;
import java.util.List;

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
	private String rentCityName;
	private String returnCityName;
	private double totalPrice;
	private List<GetOrderedServiceDto> orderedServices;

}
