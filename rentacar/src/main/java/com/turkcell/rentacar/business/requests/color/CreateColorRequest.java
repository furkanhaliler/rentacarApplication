package com.turkcell.rentacar.business.requests.color;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateColorRequest {
	
	@NotNull
	@Size(min = 2, max = 50, message = ValidationMessages.COLOR_NAME_RULE)
    private String colorName;
}
