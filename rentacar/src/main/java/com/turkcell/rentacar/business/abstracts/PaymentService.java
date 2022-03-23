package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.lists.PaymentListDto;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entities.concretes.Rent;

public interface PaymentService {

	Result add(CreatePaymentRequest createPaymentRequest, Rent rent) throws BusinessException;
	
	DataResult<List<PaymentListDto>> getAll() throws BusinessException;
}
