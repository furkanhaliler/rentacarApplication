package com.turkcell.rentacar.api.models;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.turkcell.rentacar.business.requests.orderedService.CreateOrderedServiceRequest;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.rent.CreateRentRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentModelForCorporateCustomers {

	@NotNull
	@Valid
	private CreateRentRequest createRentRequest;
	
	@Nullable
	@Valid
	private List<CreateOrderedServiceRequest> createOrderedServiceRequests;
	
	@NotNull
	@Valid
	private CreatePaymentRequest createPaymentRequest;
	
	@NotNull
	private EnumSaveCreditCard enumSaveCreditCard;
}
