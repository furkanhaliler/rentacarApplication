package com.turkcell.rentacar.business.requests.color;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateColorRequest {
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.COLOR_ID_RULE)
    private int colorId;
	
	@NotNull
	@Size(min = 2, max = 50, message = ValidationMessages.COLOR_NAME_RULE)
    private String colorName;
}
