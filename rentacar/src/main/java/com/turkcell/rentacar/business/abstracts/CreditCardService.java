package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetCreditCardDto;
import com.turkcell.rentacar.business.dtos.lists.CreditCardListDto;
import com.turkcell.rentacar.business.requests.CreditCard.CreateCreditCardRequest;
import com.turkcell.rentacar.business.requests.CreditCard.DeleteCreditCardRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CreditCardService {

	Result add(CreateCreditCardRequest createCreditCardRequest) throws BusinessException;
	
	DataResult<List<CreditCardListDto>> getAll() throws BusinessException;

	DataResult<GetCreditCardDto> getById(Integer id) throws BusinessException;

	Result delete(DeleteCreditCardRequest deleteCreditCardRequest) throws BusinessException;
	
	DataResult<List<CreditCardListDto>> getByCustomerUserId (int customerUserId) throws BusinessException;
	
	void checkIfCreditCardIdExists (int creditCardId) throws BusinessException;
}
