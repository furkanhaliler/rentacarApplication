package com.turkcell.rentacar.business.requests.color;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteColorRequest {
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.COLOR_ID_RULE)
    private int colorId;
}
