package com.turkcell.rentacar.api.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.OrderedServiceService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.requests.OrderedService.CreateOrderedServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.entities.concretes.Invoice;
import com.turkcell.rentacar.entities.concretes.Rent;

@Service
public class RentModelManager implements RentModelService{
	
	ModelMapperService mapperService;
	RentService rentService;
	OrderedServiceService orderedServiceService;
	InvoiceService invoiceService;
	PaymentService paymentService;
	
	@Autowired
	public RentModelManager(ModelMapperService mapperService, RentService rentService,
			OrderedServiceService orderedServiceService, InvoiceService invoiceService, PaymentService paymentService) {
		super();
		this.mapperService = mapperService;
		this.rentService = rentService;
		this.orderedServiceService = orderedServiceService;
		this.invoiceService = invoiceService;
		this.paymentService = paymentService;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result add(CreateRentModel createRentModel) throws BusinessException {
		
		Rent rent = this.rentService.add(createRentModel.getCreateRentRequest()).getData();
		
		for (CreateOrderedServiceRequest createOrderedServiceRequest : createRentModel.getCreateOrderedServiceRequests()) {
			
			createOrderedServiceRequest.setRentId(rent.getRentId());
			this.orderedServiceService.add(createOrderedServiceRequest);
		}
		
		createRentModel.getCreateInvoiceRequest().setRentRentId(rent.getRentId());
		
		Invoice invoice = this.invoiceService.add(createRentModel.getCreateInvoiceRequest()).getData();
		
		createRentModel.getCreatePaymentRequest().setRentId(rent.getRentId());
		
		createRentModel.getCreatePaymentRequest().setInvoiceId(invoice.getInvoiceId());
		
		createRentModel.getCreatePaymentRequest().setCustomerUserId(rent.getCustomer().getUserId());
		
		this.paymentService.add(createRentModel.getCreatePaymentRequest());
		
		return new SuccessResult(BusinessMessages.RENT_MODEL_SUCCESSFULL);
		
	}

}
