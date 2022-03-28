package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.rentacar.api.model.CreateRentModel;
import com.turkcell.rentacar.api.model.EndRentWithExtraPaymentModel;
import com.turkcell.rentacar.api.model.EnumSaveCreditCard;
import com.turkcell.rentacar.business.abstracts.CreditCardService;
import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.OrderedServiceService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.abstracts.TransactionalRentService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.requests.Invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.creditCard.CreateCreditCardRequest;
import com.turkcell.rentacar.business.requests.orderedService.CreateOrderedServiceRequest;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.entities.concretes.Invoice;
import com.turkcell.rentacar.entities.concretes.Rent;

@Service
public class TransactionalRentManager implements TransactionalRentService{
	
	private RentService rentService;
	private OrderedServiceService orderedServiceService;
	private InvoiceService invoiceService;
	private PaymentService paymentService;
	private CreditCardService creditCardService;
	
	@Autowired
	public TransactionalRentManager(RentService rentService,
			OrderedServiceService orderedServiceService, InvoiceService invoiceService, 
			PaymentService paymentService, CreditCardService creditCardService) {
		
		this.rentService = rentService;
		this.orderedServiceService = orderedServiceService;
		this.invoiceService = invoiceService;
		this.paymentService = paymentService;
		this.creditCardService = creditCardService;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result add(CreateRentModel createRentModel) throws BusinessException {
		
		Rent rent = this.rentService.add(createRentModel.getCreateRentRequest()).getData();
		
		addOrderedServicesToRent(createRentModel.getCreateOrderedServiceRequests(), rent.getRentId());
		
		CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();
		
		createInvoiceRequest.setRentRentId(rent.getRentId());
		
		Invoice invoice = this.invoiceService.add(createInvoiceRequest).getData();
		
		createRentModel.getCreatePaymentRequest().setRentId(rent.getRentId());
		createRentModel.getCreatePaymentRequest().setInvoiceId(invoice.getInvoiceId());		
		createRentModel.getCreatePaymentRequest().setCustomerUserId(rent.getCustomer().getUserId());
		
		this.paymentService.add(createRentModel.getCreatePaymentRequest());
		
		checkIfUserWantsToSaveCreditCard(createRentModel.getEnumSaveCreditCard(), createRentModel.getCreatePaymentRequest());
		
		return new SuccessResult(BusinessMessages.RENT_MODEL_SUCCESSFULL);
		
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result endRentWithExtraPayment(EndRentWithExtraPaymentModel endRentWithExtraPaymentModel) throws BusinessException {
		
		Rent rent = this.rentService.bringRentById(endRentWithExtraPaymentModel.getEndRentRequest().getRentId());
		
		double totalPrice = this.rentService.calculateExtraDaysPrice(rent.getRentId());
		
		rent.setRentReturnDate(LocalDate.now());
		
		Invoice invoice = this.invoiceService.addExtraInvoice(rent.getRentId(), totalPrice).getData();
		
		endRentWithExtraPaymentModel.getCreatePaymentRequest().setRentId(rent.getRentId());
		endRentWithExtraPaymentModel.getCreatePaymentRequest().setInvoiceId(invoice.getInvoiceId());
		endRentWithExtraPaymentModel.getCreatePaymentRequest().setCustomerUserId(rent.getCustomer().getUserId());
		
		this.paymentService.add(endRentWithExtraPaymentModel.getCreatePaymentRequest());
		
		this.rentService.endRent(endRentWithExtraPaymentModel.getEndRentRequest());
		
		return new SuccessResult(BusinessMessages.RENT_ENDED);	
	}

	@Override
	public void addOrderedServicesToRent(List<CreateOrderedServiceRequest> createOrderedServiceRequests, int rentId){
		
		for (CreateOrderedServiceRequest createOrderedServiceRequest : createOrderedServiceRequests) {
			
			createOrderedServiceRequest.setRentId(rentId);
			
			this.orderedServiceService.add(createOrderedServiceRequest);
		}		
	}

	@Override
	public void checkIfUserWantsToSaveCreditCard(EnumSaveCreditCard enumSaveCreditCard, CreatePaymentRequest createPaymentRequest) {
		
		if(enumSaveCreditCard == EnumSaveCreditCard.YES) {
			
			CreateCreditCardRequest createCreditCardRequest = new CreateCreditCardRequest();
			
			createCreditCardRequest.setCreditCardNo(createPaymentRequest.getCreatePosRequest().getCreditCardNo());
			createCreditCardRequest.setCreditCardHolder(createPaymentRequest.getCreatePosRequest().getCreditCardHolder());
			createCreditCardRequest.setExpirationMonth(createPaymentRequest.getCreatePosRequest().getExpirationMonth());
			createCreditCardRequest.setExpirationYear(createPaymentRequest.getCreatePosRequest().getExpirationYear());
			createCreditCardRequest.setCvv(createPaymentRequest.getCreatePosRequest().getCvv());
			createCreditCardRequest.setCustomerUserId(createPaymentRequest.getCustomerUserId());
			
			this.creditCardService.add(createCreditCardRequest);
		}	
	}

	
	
}
