package com.turkcell.rentacar.business.requests.brand;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {
	
	@NotNull
	@Size(min = 2, max = 50, message = ValidationMessages.BRAND_NAME_RULE)
    private String brandName;
}
