package com.turkcell.rentacar.business.requests.orderedService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedServiceRequest {
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.ORDERED_SERVICE_ID_RULE)
	private Integer additionalServiceId;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.ORDERED_SERVICE_AMOUNT_RULE)
	private Integer orderedServiceAmount;
	
	@JsonIgnore
	private int rentId;

}
