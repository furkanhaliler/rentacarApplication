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
	private LocalDate rentStartDate;
	private LocalDate rentReturnDate;
	private Double startKilometer;
	private Double endKilometer;
	private int carId;
	private String rentCityName;
	private String returnCityName;
	private List<GetOrderedServiceDto> orderedServices;
	private int customerUserId;

}
