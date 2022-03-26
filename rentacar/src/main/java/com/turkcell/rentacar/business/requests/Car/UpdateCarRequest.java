package com.turkcell.rentacar.business.requests.Car;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	@Min(1)
    private int id;
	
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
	@Min(1)
    private int brandId;
	
	@NotNull
	@Min(1)
    private int colorId;
	
	@NotNull
	@Min(1)
	private int baseCityId;

}
