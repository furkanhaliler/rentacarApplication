package com.turkcell.rentacar.business.requests.create;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateBrandRequest {
	@NotNull
	@Size(min = 2, max = 50)
    private String brandName;
}
