package com.turkcell.rentacar.business.dtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DamageListDto {

	private int damageId;
	
	private String description;
	
	private int carCarId;
}
