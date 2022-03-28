package com.turkcell.rentacar.business.requests.payment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkcell.rentacar.business.requests.pos.CreatePosRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

	@NotNull
	@Valid
	private CreatePosRequest createPosRequest;
	
	@JsonIgnore
	private int rentId;
	
	@JsonIgnore
	private int invoiceId;
	
	@JsonIgnore
	private int customerUserId;
}
