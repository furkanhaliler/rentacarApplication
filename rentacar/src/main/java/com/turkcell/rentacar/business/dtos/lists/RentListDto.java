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
	private LocalDate rentStartDate;
	private LocalDate rentReturnDate;
	private Double startKilometer;
	private Double endKilometer;
	private int carId;
	private String rentCityName;
	private String returnCityName;
	private List<GetOrderedServiceDto> orderedServices;
	private int customerUserId;
	private int invoiceId;
}
