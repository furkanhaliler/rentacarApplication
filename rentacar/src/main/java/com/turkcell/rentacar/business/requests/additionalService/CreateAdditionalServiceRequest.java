package com.turkcell.rentacar.business.requests.additionalService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditionalServiceRequest {

	@NotNull
	@Size(min = 3, max = 50, message = ValidationMessages.ADDITIONAL_SERVICE_NAME_RULE)
	private String additionalServiceName;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.ADDITIONAL_SERVICE_DAILY_PRICE_RULE)
	private double dailyPrice;
	
}
