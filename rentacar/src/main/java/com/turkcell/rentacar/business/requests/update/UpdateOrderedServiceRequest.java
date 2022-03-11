package com.turkcell.rentacar.business.requests.update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderedServiceRequest {

	@NotNull
	@Min(1)
	private int id;
	
	@Min(1)
	private int additionalServiceId;
	
	@NotNull
	@Min(1)
	private int orderedServiceAmount;
	
	@NotNull
	@Min(1)
	private int rentId;
	
}
