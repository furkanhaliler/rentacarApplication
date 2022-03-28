package com.turkcell.rentacar.business.requests.city;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCityRequest {
	
	@NotNull
	@Min(1)
    private int cityId;
	
	@NotNull
	@Size(min = 2, max = 50)
    private String cityName;

}
