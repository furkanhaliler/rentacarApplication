package com.turkcell.rentacar.business.requests.create;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateColorRequest {
	
	@NotNull
	@Size(min = 2, max = 50)
    private String colorName;
}
