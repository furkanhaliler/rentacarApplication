package com.turkcell.rentacar.business.requests.carMaintenance;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCarMaintenanceRequest {

	@NotNull
	@Min(1)
	private int MaintenanceId;
}
