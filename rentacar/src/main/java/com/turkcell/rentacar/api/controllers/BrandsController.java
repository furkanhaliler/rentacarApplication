package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.BrandListDto;
import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.requests.*;
import com.turkcell.rentacar.business.requests.create.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {

    private BrandService brandService;

    @Autowired
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateBrandRequest createBrandRequest) throws BusinessException{
        
    	return this.brandService.add(createBrandRequest);
    }

    @GetMapping("/getAll")
    public DataResult<List<BrandListDto>> getAll() throws BusinessException {
        return this.brandService.getAll();
    }

    @GetMapping("/getByBrandId/{brandId}")
    public DataResult<GetBrandDto> getByBrandId(@RequestParam("brandId") int brandId) throws BusinessException{
        return this.brandService.getByBrandId(brandId);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateBrandRequest updateBrandRequest) throws BusinessException{
        return this.brandService.update(updateBrandRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteBrandRequest deleteBrandRequest) throws BusinessException{
        return this.brandService.delete(deleteBrandRequest);
    }
}
