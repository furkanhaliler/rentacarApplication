package com.turkcell.rentacar.api.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.OrderedServiceService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.requests.create.CreateOrderedServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
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
		
		this.paymentService.add(createRentModel.getCreatePaymentRequest(), rent);
		
		createRentModel.getCreateInvoiceRequest().setRentRentId(rent.getRentId());
		
		this.invoiceService.add(createRentModel.getCreateInvoiceRequest());
		
		return new SuccessResult("Başarılı.");
		
	}

}
