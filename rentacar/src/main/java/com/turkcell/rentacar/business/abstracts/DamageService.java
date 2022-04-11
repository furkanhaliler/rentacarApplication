package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.damage.DamageListDto;
import com.turkcell.rentacar.business.dtos.damage.GetDamageDto;
import com.turkcell.rentacar.business.requests.damage.CreateDamageRequest;
import com.turkcell.rentacar.business.requests.damage.DeleteDamageRequest;
import com.turkcell.rentacar.business.requests.damage.UpdateDamageRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface DamageService {
	
	Result add(CreateDamageRequest createDamageRequest) throws BusinessException;

	DataResult<List<DamageListDto>> getAll();

	DataResult<GetDamageDto> getById(Integer id) throws BusinessException;

	Result update(UpdateDamageRequest updateDamageRequest) throws BusinessException;

	Result delete(DeleteDamageRequest deleteDamageRequest) throws BusinessException;
	
	DataResult<List<DamageListDto>> getByCarId(Integer carId) throws BusinessException;
	
	void checkIfDamageIdExists (Integer id) throws BusinessException;
}
