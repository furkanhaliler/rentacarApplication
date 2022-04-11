package com.turkcell.rentacar.business.requests.rent;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EndRentRequest {

	@NotNull
	@Min(value = 1, message = ValidationMessages.RENT_ID_RULE)
	private int rentId;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.RENT_END_KILOMETER_RULE)
	private double endKilometer;
}
