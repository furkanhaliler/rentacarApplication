package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetDamageDto;
import com.turkcell.rentacar.business.dtos.lists.DamageListDto;
import com.turkcell.rentacar.business.requests.Damage.CreateDamageRequest;
import com.turkcell.rentacar.business.requests.Damage.DeleteDamageRequest;
import com.turkcell.rentacar.business.requests.Damage.UpdateDamageRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface DamageService {
	
	Result add(CreateDamageRequest createDamageRequest) throws BusinessException;

	DataResult<List<DamageListDto>> getAll() throws BusinessException;

	DataResult<GetDamageDto> getById(Integer id) throws BusinessException;

	Result update(UpdateDamageRequest updateDamageRequest) throws BusinessException;

	Result delete(DeleteDamageRequest deleteDamageRequest) throws BusinessException;
	
	DataResult<List<DamageListDto>> getByCarId(Integer carId) throws BusinessException;
	
	void checkIfDamageIdExists (Integer id) throws BusinessException;
}
