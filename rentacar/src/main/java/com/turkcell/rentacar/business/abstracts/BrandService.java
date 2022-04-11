package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.brand.BrandListDto;
import com.turkcell.rentacar.business.dtos.brand.GetBrandDto;
import com.turkcell.rentacar.business.requests.brand.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.brand.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.brand.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import java.util.List;

public interface BrandService {

	DataResult<List<BrandListDto>> getAll();

	Result add(CreateBrandRequest createBrandRequest) throws BusinessException;

	DataResult<GetBrandDto> getByBrandId(Integer id) throws BusinessException;

	Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException;

	Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException;
	
	void checkIfBrandNameExists (String brandName) throws BusinessException;
	 
	void checkIfBrandIdExists (Integer id) throws BusinessException;
}
