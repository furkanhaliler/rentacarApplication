package com.turkcell.rentacar.business.requests.City;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCityRequest {

	@NotNull
	@Min(1)
    private int cityId;

}
