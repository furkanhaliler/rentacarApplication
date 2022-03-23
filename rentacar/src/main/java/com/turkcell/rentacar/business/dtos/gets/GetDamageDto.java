package com.turkcell.rentacar.business.dtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDamageDto {

	private int damageId;

	private String description;

	private int carCarId;
}
