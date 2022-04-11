package com.turkcell.rentacar.business.requests.car;

import javax.validation.constraints.*;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@NotNull
	@Min(value = 100, message = ValidationMessages.CAR_DAILY_PRICE_RULE)
    private int dailyPrice;
	
	@NotNull
	@Min(value = 2000, message = ValidationMessages.CAR_MODEL_YEAR_RULE)
    private int modelYear;
	
	@NotNull
	@Size(min = 2, max = 50, message = ValidationMessages.CAR_DESCRIPTION_RULE)
    private String description;
	
	@NotNull
	@Min(value = 0, message = ValidationMessages.CAR_KILOMETER_RULE)
	private double kilometer;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.CAR_BRAND_ID_RULE)
    private int brandId;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.CAR_COLOR_ID_RULE)
    private int colorId;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.CAR_BASE_CITY_ID_RULE)
	private int baseCityId;
}
