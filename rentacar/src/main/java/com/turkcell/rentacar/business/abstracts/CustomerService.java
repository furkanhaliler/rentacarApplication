package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.customer.CustomerListDto;
import com.turkcell.rentacar.business.dtos.customer.GetCustomerDto;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;

public interface CustomerService {

	DataResult<List<CustomerListDto>> getAll();

	DataResult<GetCustomerDto> getByUserId(Integer id) throws BusinessException;
	 
	void checkIfCustomerIdExists (Integer id) throws BusinessException;
}
