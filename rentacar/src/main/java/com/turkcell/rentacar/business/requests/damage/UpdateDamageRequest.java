package com.turkcell.rentacar.business.requests.damage;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDamageRequest {
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.DAMAGE_ID_RULE)
	private int damageId;
	
	@NotNull
	@Size(min = 4, message = ValidationMessages.DAMAGE_DESCRIPTION_RULE)
	private String description;
	

}
