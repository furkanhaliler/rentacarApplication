package com.turkcell.rentacar.business.requests.update;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateRentRequest {

	@NotNull
	@Min(1)
	private int rentId;
	
	@NotNull
	private LocalDate startDate;
	
	@NotNull
	private LocalDate returnDate;
	
}
