package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.PosService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.adapters.posAdapters.IsBankPosAdapter;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.gets.GetPaymentDto;
import com.turkcell.rentacar.business.dtos.lists.PaymentListDto;
import com.turkcell.rentacar.business.outServices.HalkBankPosManager;
import com.turkcell.rentacar.business.outServices.IsBankPosManager;
import com.turkcell.rentacar.business.requests.Payment.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.Pos.CreatePosRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentacar.entities.concretes.Payment;
import com.turkcell.rentacar.entities.concretes.Rent;

@Service
public class PaymentManager implements PaymentService {

	
	
	private PaymentDao paymentDao;
	private ModelMapperService modelMapperService;
	private PosService posService;
	private RentService rentService;
	private CustomerService customerService;
	private InvoiceService invoiceService;
	
	
	@Autowired
	public PaymentManager(PaymentDao paymentDao, ModelMapperService modelMapperService, PosService posService, RentService rentService,
			CustomerService customerService, InvoiceService invoiceService) {

		this.paymentDao = paymentDao;
		this.modelMapperService = modelMapperService;
		this.posService = posService;
		this.rentService = rentService;	
		this.customerService = customerService;
		this.invoiceService = invoiceService;
	}
	
	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) throws BusinessException {
		
		PosService posService2 = new IsBankPosAdapter();
		
		if(posService2.pay(createPaymentRequest.getCreatePosRequest())) {
		
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		
		this.paymentDao.save(payment);
		
		return new SuccessResult(BusinessMessages.PAYMENT_SUCCESSFULL);
		
		}
		
		throw new BusinessException(BusinessMessages.PAYMENT_UNSUCCESSFULL);
	}

	@Override
	public DataResult<List<PaymentListDto>> getAll() throws BusinessException {
		
		List<Payment> result = this.paymentDao.findAll();
		
		List<PaymentListDto> response = result.stream().map(payment -> this.modelMapperService
				.forDto().map(payment, PaymentListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<PaymentListDto>>(response, BusinessMessages.PAYMENTS_LISTED);
	}

	@Override
	public DataResult<GetPaymentDto> getByPaymentId(int paymentId) throws BusinessException {
		
		checkIfPaymentIdExists(paymentId);
		
		Payment result = this.paymentDao.getById(paymentId);
		
		GetPaymentDto response = this.modelMapperService.forDto().map(result, GetPaymentDto.class);
		
		return new SuccessDataResult<GetPaymentDto>(response, BusinessMessages.PAYMENT_FOUND_BY_ID);
	}

	@Override
	public DataResult<List<PaymentListDto>> getByCustomerUserId(int userId) throws BusinessException {
		
		this.customerService.checkIfCustomerIdExists(userId);
		
		List<Payment> result = this.paymentDao.findByCustomerUserId(userId);
		
		List<PaymentListDto> response = result.stream().map(payment -> this.modelMapperService
				.forDto().map(payment, PaymentListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<PaymentListDto>>(response, BusinessMessages.PAYMENTS_LISTED_BY_CUSTOMER_ID);
	}

	@Override
	public DataResult<GetPaymentDto> getByInvoiceId(int invoiceId) throws BusinessException {
		
		this.invoiceService.checkIfInvoiceIdExists(invoiceId);
		
		Payment payment = this.paymentDao.findByInvoiceInvoiceId(invoiceId);
		
		GetPaymentDto response = this.modelMapperService.forDto().map(payment, GetPaymentDto.class);
		
		return new SuccessDataResult<GetPaymentDto>(response, BusinessMessages.PAYMENT_FOUND_BY_INVOICE_ID);
	}

	@Override
	public DataResult<List<PaymentListDto>> getByRentId(int rentId) throws BusinessException {
		
		this.rentService.checkIfRentIdExists(rentId);
		
		List<Payment> result = this.paymentDao.findByRentRentId(rentId);
		
		List<PaymentListDto> response = result.stream().map(payment -> this.modelMapperService
				.forDto().map(payment, PaymentListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<PaymentListDto>>(response, BusinessMessages.PAYMENT_FOUND_BY_RENT_ID);
	}



	@Override
	public void checkIfPaymentIdExists(int paymentId) throws BusinessException {
		
		if(!this.paymentDao.existsById(paymentId)) {
			
			throw new BusinessException(BusinessMessages.PAYMENT_NOT_FOUND);
		}
	}

}




