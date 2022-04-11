package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.payment.GetPaymentDto;
import com.turkcell.rentacar.business.dtos.payment.PaymentListDto;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.pos.CreatePosRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface PaymentService {

	Result add(CreatePaymentRequest createPaymentRequest) throws BusinessException;
	
	DataResult<List<PaymentListDto>> getAll();
	
	DataResult<GetPaymentDto> getByPaymentId (int paymentId) throws BusinessException;
	
	DataResult<List<PaymentListDto>> getByCustomerUserId(int userId) throws BusinessException;
	
	DataResult<GetPaymentDto> getByInvoiceId (int invoiceId) throws BusinessException;
	
	DataResult<List<PaymentListDto>> getByRentId(int rentId) throws BusinessException;
	
	void makePayment(CreatePosRequest createPosRequest, double paymentAmount) throws BusinessException;
	
	void checkIfPaymentIdExists(int paymentId) throws BusinessException;
	
}
