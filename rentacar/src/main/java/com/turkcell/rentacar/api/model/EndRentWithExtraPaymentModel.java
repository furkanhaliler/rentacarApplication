package com.turkcell.rentacar.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkcell.rentacar.business.requests.Payment.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.Pos.CreatePosRequest;
import com.turkcell.rentacar.business.requests.Rent.EndRentRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndRentWithExtraPaymentModel {

	@NotNull
	private EndRentRequest endRentRequest;
	
	@NotNull
	@Valid
	private CreatePaymentRequest createPaymentRequest;

}
