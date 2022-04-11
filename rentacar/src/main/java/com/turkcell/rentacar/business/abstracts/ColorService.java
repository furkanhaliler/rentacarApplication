package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.color.ColorListDto;
import com.turkcell.rentacar.business.dtos.color.GetColorDto;
import com.turkcell.rentacar.business.requests.color.CreateColorRequest;
import com.turkcell.rentacar.business.requests.color.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.color.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import java.util.List;

public interface ColorService {
	
	DataResult<List<ColorListDto>> getAll();

	Result add(CreateColorRequest createColorRequest) throws BusinessException;

	DataResult<GetColorDto> getById(Integer id) throws BusinessException;

	Result update(UpdateColorRequest updateColorRequest) throws BusinessException;

	Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException;
	
	void checkIfColorNameExists (String colorName) throws BusinessException;
	
	void checkIfColorIdExists (Integer id) throws BusinessException;

}
