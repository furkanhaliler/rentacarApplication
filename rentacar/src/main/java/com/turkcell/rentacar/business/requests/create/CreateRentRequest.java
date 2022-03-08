package com.turkcell.rentacar.business.requests.create;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateRentRequest {
	
	@NotNull	
	@Min(1)
	private int carId;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate returnDate;
	

}
