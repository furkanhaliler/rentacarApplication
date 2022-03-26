package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetCorporateCustomerDto;
import com.turkcell.rentacar.business.dtos.lists.CorporateCustomerListDto;
import com.turkcell.rentacar.business.requests.CorporateCustomer.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.CorporateCustomer.DeleteCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.CorporateCustomer.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CorporateCustomerService {

	DataResult<List<CorporateCustomerListDto>> getAll() throws BusinessException;

	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException;

	DataResult<GetCorporateCustomerDto> getByUserId(Integer id) throws BusinessException;

	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException;

	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException;
	 
	void checkIfCorporateCustomerIdExists (Integer id) throws BusinessException;
	
	
}
