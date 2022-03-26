package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.gets.GetBrandDto;
import com.turkcell.rentacar.business.dtos.lists.BrandListDto;
import com.turkcell.rentacar.business.requests.Brand.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.Brand.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.Brand.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import java.util.List;

public interface BrandService {

	DataResult<List<BrandListDto>> getAll() throws BusinessException;

	Result add(CreateBrandRequest createBrandRequest) throws BusinessException;

	DataResult<GetBrandDto> getByBrandId(Integer id) throws BusinessException;

	Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException;

	Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException;
	
	void checkIfBrandNameExists (String brandName) throws BusinessException;
	 
	void checkIfBrandIdExists (Integer id) throws BusinessException;
}
