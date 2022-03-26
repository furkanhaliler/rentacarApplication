package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetIndividualCustomerDto;
import com.turkcell.rentacar.business.dtos.lists.IndividualCustomerListDto;
import com.turkcell.rentacar.business.requests.IndividualCustomer.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.IndividualCustomer.DeleteIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.IndividualCustomer.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface IndividualCustomerService {


	DataResult<List<IndividualCustomerListDto>> getAll() throws BusinessException;

	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException;

	DataResult<GetIndividualCustomerDto> getByUserId(Integer id) throws BusinessException;

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException;

	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException;
	 
	void checkIfIndividualCustomerIdExists (Integer id) throws BusinessException;
}
