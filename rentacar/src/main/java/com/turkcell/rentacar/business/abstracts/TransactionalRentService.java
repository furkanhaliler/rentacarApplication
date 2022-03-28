package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.api.model.CreateRentModel;
import com.turkcell.rentacar.api.model.EndRentWithExtraPaymentModel;
import com.turkcell.rentacar.api.model.EnumSaveCreditCard;
import com.turkcell.rentacar.business.requests.orderedService.CreateOrderedServiceRequest;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface TransactionalRentService {

	Result add(CreateRentModel createRentModel) throws BusinessException;
	
	Result endRentWithExtraPayment(EndRentWithExtraPaymentModel endRentWithExtraPaymentModel) throws BusinessException;
	
	void addOrderedServicesToRent (List<CreateOrderedServiceRequest> createOrderedServiceRequests, int rentId);
	
	void checkIfUserWantsToSaveCreditCard(EnumSaveCreditCard enumSaveCreditCard, CreatePaymentRequest createPaymentRequest);
	
}
