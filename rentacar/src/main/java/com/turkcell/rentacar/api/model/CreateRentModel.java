package com.turkcell.rentacar.api.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.turkcell.rentacar.business.requests.create.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.create.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.create.CreateOrderedServiceRequest;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentModel {

	@NotNull
	CreateRentRequest createRentRequest;
	List<CreateOrderedServiceRequest> createOrderedServiceRequests;
	CreateInvoiceRequest createInvoiceRequest;
	@NotNull
	CreatePaymentRequest createPaymentRequest;
	
}
