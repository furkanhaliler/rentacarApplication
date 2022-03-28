package com.turkcell.rentacar.business.requests.orderedService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedServiceRequest {
	
	@NotNull
	@Min(1)
	private int additionalServiceId;
	
	@NotNull
	@Min(1)
	private int orderedServiceAmount;
	
	@JsonIgnore
	private int rentId;

}
