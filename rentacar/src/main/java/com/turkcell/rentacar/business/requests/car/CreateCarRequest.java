package com.turkcell.rentacar.business.requests.car;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@NotNull
	@Min(100)
    private int dailyPrice;
	
	@NotNull
	@Min(2000)
    private int modelYear;
	
	@NotNull
	@Size(min = 2, max = 50)
    private String description;
	
	@NotNull
	@Min(0)
	private double kilometer;
	
	@NotNull
	@Min(1)
    private int brandId;
	
	@NotNull
	@Min(1)
    private int colorId;
	
	@NotNull
	@Min(1)
	private int baseCityId;
}
