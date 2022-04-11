package com.turkcell.rentacar.business.requests.additionalService;

import javax.validation.constraints.Min;

import javax.validation.constraints.NotNull;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteAdditionalServiceRequest {

	@NotNull
	@Min(value = 1, message = ValidationMessages.ADDITIONAL_SERVICE_ID_RULE)
	private int additionalServiceId;
}
