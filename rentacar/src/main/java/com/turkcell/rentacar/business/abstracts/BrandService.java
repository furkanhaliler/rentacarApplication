package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.BrandListDto;
import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.requests.create.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.update.UpdateBrandRequest;
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
}
