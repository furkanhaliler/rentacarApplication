package com.turkcell.rentacar.business.dtos.damage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DamageDTO {

	private int damageId;	
	private String description;
}
