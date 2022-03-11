package com.turkcell.rentacar.business.dtos.lists;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetOrderedServiceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentListDto {

	private int rentId;
	private LocalDate startDate;
	private LocalDate returnDate;
	private int carId;
	private String rentCityName;
	private String returnCityName;
	private double totalPrice;
	private List<GetOrderedServiceDto> orderedServices;
}
