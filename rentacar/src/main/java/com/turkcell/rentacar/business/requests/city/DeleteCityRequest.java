package com.turkcell.rentacar.business.requests.city;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCityRequest {

	@NotNull
	@Min(value = 1, message = ValidationMessages.CITY_ID_RULE)
    private int cityId;

}
