package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.turkcell.rentacar.aspects.ToLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.brand.BrandListDto;
import com.turkcell.rentacar.business.dtos.brand.GetBrandDto;
import com.turkcell.rentacar.business.requests.brand.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.brand.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.brand.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.brand.BrandAlreadyExistsException;
import com.turkcell.rentacar.core.exceptions.brand.BrandNotFoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentacar.entities.concretes.Brand;

@Service
@Slf4j
public class BrandManager implements BrandService {

  private BrandDao brandDao;
  private ModelMapperService modelMapperService;

  @Autowired
  public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {

    this.brandDao = brandDao;
    this.modelMapperService = modelMapperService;
  }

  @Override
  public DataResult<List<BrandListDto>> getAll() {

    List<Brand> result = this.brandDao.findAll();

    List<BrandListDto> response =
        result.stream()
            .map(brand -> this.modelMapperService.forDto().map(brand, BrandListDto.class))
            .collect(Collectors.toList());

    return new SuccessDataResult<List<BrandListDto>>(response, BusinessMessages.BRANDS_LISTED);
  }

  @Override
  public Result add(CreateBrandRequest createBrandRequest) throws BusinessException {

    checkIfBrandNameExists(createBrandRequest.getBrandName());

    Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);

    this.brandDao.save(brand);

//        		if(true){
//        			throw new BusinessException("HATA!!!");
//        		}
dneeme();
    log.info("Brand saved: " + createBrandRequest.getBrandName());

    return new SuccessResult(BusinessMessages.BRAND_ADDED);
  }

  @Override
  @ToLog
  public DataResult<GetBrandDto> getByBrandId(Integer id) throws BusinessException {

    checkIfBrandIdExists(id);

    Brand foundBrand = brandDao.getById(id);

    GetBrandDto response = this.modelMapperService.forDto().map(foundBrand, GetBrandDto.class);

    return new SuccessDataResult<GetBrandDto>(response, BusinessMessages.BRAND_FOUND_BY_ID);
  }

  @Override
  @ToLog
  public Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException {

    checkIfBrandIdExists(updateBrandRequest.getBrandId());
    checkIfBrandNameExists(updateBrandRequest.getBrandName());

    Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);

    this.brandDao.save(brand);

    return new SuccessResult(BusinessMessages.BRAND_UPDATED);
  }

  @Override
  public Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException {

    checkIfBrandIdExists(deleteBrandRequest.getBrandId());

    this.brandDao.deleteById(deleteBrandRequest.getBrandId());

    return new SuccessResult(BusinessMessages.BRAND_DELETED);
  }

  @Override
  public void checkIfBrandNameExists(String brandName) throws BusinessException {

    if (this.brandDao.existsBrandByBrandNameIgnoreCase(brandName)) {

      throw new BrandAlreadyExistsException(BusinessMessages.BRAND_NAME_EXISTS);
    }
  }

  @Override
  public void checkIfBrandIdExists(Integer id) throws BusinessException {

    if (!this.brandDao.existsById(id)) {

      throw new BrandNotFoundException(BusinessMessages.BRAND_NOT_FOUND);
    }
  }
@ToLog
public void dneeme(){}

}
