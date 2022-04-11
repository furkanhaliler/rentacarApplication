package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.individualCustomer.GetIndividualCustomerDto;
import com.turkcell.rentacar.business.dtos.individualCustomer.IndividualCustomerListDto;
import com.turkcell.rentacar.business.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.individualCustomer.DeleteIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface IndividualCustomerService {


	DataResult<List<IndividualCustomerListDto>> getAll();

	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException;

	DataResult<GetIndividualCustomerDto> getByUserId(Integer id) throws BusinessException;

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException;

	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException;
	 
	void checkIfIndividualCustomerIdExists (Integer id) throws BusinessException;
	
	void checkIfNationalIdentityAlreadyExists(String nationalIdentity) throws BusinessException;
}
