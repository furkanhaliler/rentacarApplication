package com.turkcell.rentacar.business.requests.rent;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeleteRentRequest {
	
	
	@NotNull	
	@Min(1)
	private int rentId;
	

}
