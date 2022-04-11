package com.turkcell.rentacar.business.requests.car;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateCarRequest {

	@NotNull
	@Min(value = 1, message = ValidationMessages.CAR_ID_RULE)
    private int id;
	
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
	@Min(value = 1, message = ValidationMessages.CAR_BRAND_ID_RULE)
    private int brandId;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.CAR_COLOR_ID_RULE)
    private int colorId;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.CAR_BASE_CITY_ID_RULE)
	private int baseCityId;

}
