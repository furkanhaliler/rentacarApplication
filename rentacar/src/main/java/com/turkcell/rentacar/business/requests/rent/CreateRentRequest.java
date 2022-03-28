package com.turkcell.rentacar.business.requests.rent;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRentRequest {
	
	@NotNull	
	@Min(1)
	private int carId;
	
	@NotNull
	private LocalDate rentStartDate;
	
	@NotNull
	private LocalDate rentReturnDate;
	
	@NotNull
	@Min(1)
	private int rentCityId;
	
	@NotNull
	@Min(1)
	private int returnCityId;
	
	@NotNull
	@Min(1)
	private int customerUserId;
	
}
