package com.turkcell.rentacar.business.requests.brand;

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
public class UpdateBrandRequest {
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.BRAND_ID_RULE)
    private int brandId;
	
	@NotNull
	@Size(min = 2, max = 50, message = ValidationMessages.BRAND_NAME_RULE)
    private String brandName;
}
