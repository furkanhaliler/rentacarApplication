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
public class UpdateRentRequest {

	@NotNull
	@Min(1)
	private int rentId;
	
	@NotNull
	private LocalDate rentReturnDate;
	
}
