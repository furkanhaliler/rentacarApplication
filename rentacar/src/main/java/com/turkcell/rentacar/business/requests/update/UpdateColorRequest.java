package com.turkcell.rentacar.business.requests.update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateColorRequest {
	
	@NotNull
	@Min(1)
    private int colorId;
	
	@NotNull
	@Size(min = 2, max = 50)
    private String colorName;
}
