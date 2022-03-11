package com.turkcell.rentacar.business.requests.update;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRentRequest {

	@NotNull
	@Min(1)
	private int rentId;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate returnDate;
	
	@NotNull
	@Min(1)
	private int carId;
	
	@NotNull
	@Min(1)
	private int rentCityId;
	
	@NotNull
	@Min(1)
	private int returnCityId;
}
