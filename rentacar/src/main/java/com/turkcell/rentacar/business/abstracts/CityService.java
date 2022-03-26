package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetCityDto;
import com.turkcell.rentacar.business.dtos.lists.CityListDto;
import com.turkcell.rentacar.business.requests.City.CreateCityRequest;
import com.turkcell.rentacar.business.requests.City.DeleteCityRequest;
import com.turkcell.rentacar.business.requests.City.UpdateCityRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CityService {
	
	DataResult<List<CityListDto>> getAll() throws BusinessException;

	Result add(CreateCityRequest createCityRequest) throws BusinessException;

	DataResult<GetCityDto> getById(Integer id) throws BusinessException;

	Result update(UpdateCityRequest updateCityRequest) throws BusinessException;

	Result delete(DeleteCityRequest deleteCityRequest) throws BusinessException;
	
	void checkIfCityNameExists (String cityName) throws BusinessException;
	
	void checkIfCityIdExists (Integer id) throws BusinessException;

}
