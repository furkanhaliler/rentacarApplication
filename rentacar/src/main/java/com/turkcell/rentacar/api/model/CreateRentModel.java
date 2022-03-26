package com.turkcell.rentacar.api.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.turkcell.rentacar.business.requests.AdditionalService.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.Invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.OrderedService.CreateOrderedServiceRequest;
import com.turkcell.rentacar.business.requests.Payment.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.Pos.CreatePosRequest;
import com.turkcell.rentacar.business.requests.Rent.CreateRentRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentModel {

	@NotNull
	private CreateRentRequest createRentRequest;
	
	@NotNull
	private List<CreateOrderedServiceRequest> createOrderedServiceRequests;
	
	private CreateInvoiceRequest createInvoiceRequest;
	
	@NotNull
	private CreatePaymentRequest createPaymentRequest;
	
}
