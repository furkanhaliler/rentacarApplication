package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.api.models.CreateRentModelForCorporateCustomers;
import com.turkcell.rentacar.api.models.CreateRentModelForIndividualCustomers;
import com.turkcell.rentacar.api.models.EndRentWithExtraPaymentModel;
import com.turkcell.rentacar.api.models.EnumSaveCreditCard;
import com.turkcell.rentacar.api.models.UpdateRentModel;
import com.turkcell.rentacar.business.requests.orderedService.CreateOrderedServiceRequest;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entities.concretes.Invoice;
import com.turkcell.rentacar.entities.concretes.Rent;

public interface TransactionalService {

	Result createRentForIndividualCustomers(CreateRentModelForIndividualCustomers createRentModelForIndividualCustomers) throws BusinessException;
	
	Result createRentForCorporateCustomers(CreateRentModelForCorporateCustomers createRentModelForCorporateCustomers) throws BusinessException;
	
	Result endRentWithExtraPayment(EndRentWithExtraPaymentModel endRentWithExtraPaymentModel) throws BusinessException;
	
	Result updateRent(UpdateRentModel updateRentModel) throws BusinessException;
	
	void addOrderedServicesToRent (List<CreateOrderedServiceRequest> createOrderedServiceRequests, int rentId);
	
	void checkIfUserWantsToSaveCreditCard(EnumSaveCreditCard enumSaveCreditCard, CreatePaymentRequest createPaymentRequest) throws BusinessException;
	
	void checkIfExtraPaymentNeeded(double price) throws BusinessException;
	
	void setCreatePaymentRequestFields(CreatePaymentRequest createPaymentRequest, Rent rent, Invoice invoice);
	
	
}
