package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.rentacar.api.models.CreateRentModelForCorporateCustomers;
import com.turkcell.rentacar.api.models.CreateRentModelForIndividualCustomers;
import com.turkcell.rentacar.api.models.EndRentWithExtraPaymentModel;
import com.turkcell.rentacar.api.models.EnumSaveCreditCard;
import com.turkcell.rentacar.api.models.UpdateRentModel;
import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.CityService;
import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.abstracts.CreditCardService;
import com.turkcell.rentacar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.OrderedServiceService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.abstracts.TransactionalService;
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
public class TransactionalManager implements TransactionalService{
	
	private RentService rentService;
	private OrderedServiceService orderedServiceService;
	private InvoiceService invoiceService;
	private PaymentService paymentService;
	private CreditCardService creditCardService;
	private CarService carService;
	private CityService cityService;
	private AdditionalServiceService additionalServiceService;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public TransactionalManager(RentService rentService, OrderedServiceService orderedServiceService, 
			InvoiceService invoiceService, PaymentService paymentService, CreditCardService creditCardService,
			CarService carService, CityService cityService, AdditionalServiceService additionalServiceService, 
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService) {
		
		this.rentService = rentService;
		this.orderedServiceService = orderedServiceService;
		this.invoiceService = invoiceService;
		this.paymentService = paymentService;
		this.creditCardService = creditCardService;
		this.carService = carService;
		this.cityService = cityService;
		this.additionalServiceService = additionalServiceService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result createRentForIndividualCustomers(CreateRentModelForIndividualCustomers createRentModelForIndividualCustomers) 
			throws BusinessException {
		
		this.carService.checkIfCarIdExists(createRentModelForIndividualCustomers.getCreateRentRequest().getCarId());
		this.cityService.checkIfCityIdExists(createRentModelForIndividualCustomers.getCreateRentRequest().getRentCityId());
		this.cityService.checkIfCityIdExists(createRentModelForIndividualCustomers.getCreateRentRequest().getReturnCityId());
		this.individualCustomerService.checkIfIndividualCustomerIdExists(createRentModelForIndividualCustomers.getCreateRentRequest().getCustomerUserId());
		this.additionalServiceService.checkIfAdditionalServiceIdExistsOnOrderedServiceList(createRentModelForIndividualCustomers.getCreateOrderedServiceRequests());
		
		Rent rent = this.rentService.add(createRentModelForIndividualCustomers.getCreateRentRequest()).getData();
		
		addOrderedServicesToRent(createRentModelForIndividualCustomers.getCreateOrderedServiceRequests(), rent.getRentId());
		
		CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();
		
		createInvoiceRequest.setRentRentId(rent.getRentId());
		
		Invoice invoice = this.invoiceService.addForIndividualCustomers(createInvoiceRequest).getData();
		
		setCreatePaymentRequestFields(createRentModelForIndividualCustomers.getCreatePaymentRequest(), rent, invoice);
		
		this.paymentService.add(createRentModelForIndividualCustomers.getCreatePaymentRequest());
		
		checkIfUserWantsToSaveCreditCard(createRentModelForIndividualCustomers.getEnumSaveCreditCard(), 
				createRentModelForIndividualCustomers.getCreatePaymentRequest());
		
		return new SuccessResult(BusinessMessages.RENT_MODEL_SUCCESSFULL + invoice.getTotalPrice());		
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result createRentForCorporateCustomers(CreateRentModelForCorporateCustomers createRentModelForCorporateCustomers) 
			throws BusinessException {
		
		this.carService.checkIfCarIdExists(createRentModelForCorporateCustomers.getCreateRentRequest().getCarId());
		this.cityService.checkIfCityIdExists(createRentModelForCorporateCustomers.getCreateRentRequest().getRentCityId());
		this.cityService.checkIfCityIdExists(createRentModelForCorporateCustomers.getCreateRentRequest().getReturnCityId());
		this.corporateCustomerService.checkIfCorporateCustomerIdExists(createRentModelForCorporateCustomers.getCreateRentRequest().getCustomerUserId());
		this.additionalServiceService.checkIfAdditionalServiceIdExistsOnOrderedServiceList(createRentModelForCorporateCustomers.getCreateOrderedServiceRequests());
		
		Rent rent = this.rentService.add(createRentModelForCorporateCustomers.getCreateRentRequest()).getData();
		
		addOrderedServicesToRent(createRentModelForCorporateCustomers.getCreateOrderedServiceRequests(), rent.getRentId());
		
		CreateInvoiceRequest createInvoiceRequest = new CreateInvoiceRequest();
		
		createInvoiceRequest.setRentRentId(rent.getRentId());
		
		Invoice invoice = this.invoiceService.addForCorporateCustomers(createInvoiceRequest).getData();
		
		setCreatePaymentRequestFields(createRentModelForCorporateCustomers.getCreatePaymentRequest(), rent, invoice);
		
		this.paymentService.add(createRentModelForCorporateCustomers.getCreatePaymentRequest());
		
		checkIfUserWantsToSaveCreditCard(createRentModelForCorporateCustomers.getEnumSaveCreditCard(), 
				createRentModelForCorporateCustomers.getCreatePaymentRequest());
		
		return new SuccessResult(BusinessMessages.RENT_MODEL_SUCCESSFULL + invoice.getTotalPrice());		
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result endRentWithExtraPayment(EndRentWithExtraPaymentModel endRentWithExtraPaymentModel) throws BusinessException {
		
		this.rentService.checkIfRentIdExists(endRentWithExtraPaymentModel.getEndRentRequest().getRentId());
		
		Rent rent = this.rentService.bringRentById(endRentWithExtraPaymentModel.getEndRentRequest().getRentId());
		
		this.rentService.checkIfRentAlreadyEnded(rent);
		
		double totalPrice = this.rentService.calculateExtraDaysPrice(rent.getRentId(), LocalDate.now());
		
		checkIfExtraPaymentNeeded(totalPrice);
		
		rent.setRentReturnDate(LocalDate.now());
		
		Invoice invoice = this.invoiceService.addExtraInvoice(rent.getRentId(), totalPrice).getData();
		
		setCreatePaymentRequestFields(endRentWithExtraPaymentModel.getCreatePaymentRequest(), rent, invoice);
		
		this.paymentService.add(endRentWithExtraPaymentModel.getCreatePaymentRequest());
		
		this.rentService.endRent(endRentWithExtraPaymentModel.getEndRentRequest());
		
		checkIfUserWantsToSaveCreditCard(endRentWithExtraPaymentModel.getEnumSaveCreditCard(),
				endRentWithExtraPaymentModel.getCreatePaymentRequest());
		
		return new SuccessResult(BusinessMessages.RENT_ENDED_WITH_PAYMENT + totalPrice);	
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result updateRent(UpdateRentModel updateRentModel) throws BusinessException {
		
		this.rentService.checkIfRentIdExists(updateRentModel.getUpdateRentRequest().getRentId());

		Rent rent = this.rentService.bringRentById(updateRentModel.getUpdateRentRequest().getRentId());	
		
		this.rentService.checkIfRentAlreadyEnded(rent);
		
		addOrderedServicesToRent(updateRentModel.getCreateOrderedServiceRequests(), rent.getRentId());
		
		double totalPrice = this.rentService.calculateExtraDaysPrice(
				rent.getRentId(), updateRentModel.getUpdateRentRequest().getRentReturnDate());
		
		this.rentService.update(updateRentModel.getUpdateRentRequest());
		
		Invoice invoice = this.invoiceService.addExtraInvoice(rent.getRentId(), totalPrice).getData();
		
		setCreatePaymentRequestFields(updateRentModel.getCreatePaymentRequest(), rent, invoice);
		
		this.paymentService.add(updateRentModel.getCreatePaymentRequest());
		
		checkIfUserWantsToSaveCreditCard(updateRentModel.getEnumSaveCreditCard(), updateRentModel.getCreatePaymentRequest());
		
		return new SuccessResult(BusinessMessages.RENT_UPDATED + totalPrice);	
	}

	@Override
	public void addOrderedServicesToRent(List<CreateOrderedServiceRequest> createOrderedServiceRequests, int rentId){
		
		if(createOrderedServiceRequests == null) {
			
			return;
		}
		
		for (CreateOrderedServiceRequest createOrderedServiceRequest : createOrderedServiceRequests) {
			
			createOrderedServiceRequest.setRentId(rentId);
			
			this.orderedServiceService.add(createOrderedServiceRequest);
		}		
	}

	@Override
	public void checkIfUserWantsToSaveCreditCard(EnumSaveCreditCard enumSaveCreditCard, CreatePaymentRequest createPaymentRequest) throws BusinessException {
		
		if(enumSaveCreditCard == EnumSaveCreditCard.YES || enumSaveCreditCard == EnumSaveCreditCard.yes) {
			
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

	@Override
	public void checkIfExtraPaymentNeeded(double price) throws BusinessException {
		
		if(!(price > 0)) {
			
			throw new BusinessException(BusinessMessages.NO_NEED_FOR_EXTRA_PAYMENT);
		}	
	}

	@Override
	public void setCreatePaymentRequestFields(CreatePaymentRequest createPaymentRequest,Rent rent, Invoice invoice) {
		
		createPaymentRequest.setRentId(rent.getRentId());
		createPaymentRequest.setInvoiceId(invoice.getInvoiceId());		
		createPaymentRequest.setCustomerUserId(rent.getCustomer().getUserId());
		createPaymentRequest.setPaymentAmount(invoice.getTotalPrice());		
	}
	
	
}
