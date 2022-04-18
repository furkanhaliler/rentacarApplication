package com.turkcell.rentacar.business.requests.rent;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteRentRequest {
	
	@NotNull	
	@Min(value = 1, message = ValidationMessages.RENT_ID_RULE)
	private int rentId;
	

}
