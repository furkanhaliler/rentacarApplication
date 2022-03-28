package com.turkcell.rentacar.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.rent.EndRentRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndRentWithExtraPaymentModel {

	@NotNull
	@Valid
	private EndRentRequest endRentRequest;
	
	@NotNull
	@Valid
	private CreatePaymentRequest createPaymentRequest;

}
