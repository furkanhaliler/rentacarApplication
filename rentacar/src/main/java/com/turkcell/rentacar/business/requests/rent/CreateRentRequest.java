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
public class CreateRentRequest {
	
	@NotNull	
	@Min(value = 1, message = ValidationMessages.RENT_CAR_ID_RULE)
	private int carId;
	
	@NotNull
	private LocalDate rentStartDate;
	
	@NotNull
	private LocalDate rentReturnDate;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.RENT_RENT_CITY_ID_RULE)
	private int rentCityId;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.RENT_RETURN_CITY_ID_RULE)
	private int returnCityId;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.RENT_CUSTOMER_ID_RULE)
	private int customerUserId;
	
}
