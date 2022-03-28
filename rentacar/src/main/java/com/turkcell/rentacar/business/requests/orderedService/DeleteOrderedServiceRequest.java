package com.turkcell.rentacar.business.requests.orderedService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderedServiceRequest {

	@NotNull
	@Min(1)
	private int orderedServiceId;
}
