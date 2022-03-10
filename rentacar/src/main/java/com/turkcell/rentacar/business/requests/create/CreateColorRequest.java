package com.turkcell.rentacar.business.requests.create;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateColorRequest {
	
	@NotNull
	@Size(min = 2, max = 50)
    private String colorName;
}
