package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.lists.AdditionalServiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateAdditionalServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface AdditionalServiceService {

	DataResult<List<AdditionalServiceListDto>> getAll() throws BusinessException;

	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException;

	DataResult<GetAdditionalServiceDto> getByAdditionalServiceId(Integer id) throws BusinessException;

	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException;

	Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException;
	
	void checkIfAdditionalServiceNameExists (String additionalServiceName) throws BusinessException;
	 
	void checkIfAdditionalServiceIdExists (Integer id) throws BusinessException;
}