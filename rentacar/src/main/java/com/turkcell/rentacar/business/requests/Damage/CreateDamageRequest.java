package com.turkcell.rentacar.business.requests.Damage;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDamageRequest {


	@NotNull
	@Size(min = 4)
	private String description;
	
	@NotNull
	@Min(1)
	private int carCarId;
}
