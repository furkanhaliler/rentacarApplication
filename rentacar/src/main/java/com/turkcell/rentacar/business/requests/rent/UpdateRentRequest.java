package com.turkcell.rentacar.business.requests.rent;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRentRequest {

	@NotNull
	@Min(value = 1, message = ValidationMessages.RENT_ID_RULE)
	private int rentId;
	
	@NotNull
	private LocalDate rentReturnDate;
	
}
