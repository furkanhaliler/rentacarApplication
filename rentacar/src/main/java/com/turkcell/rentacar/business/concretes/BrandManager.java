package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.BrandListDto;
import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.requests.create.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BrandAlreadyExistException;
import com.turkcell.rentacar.core.exceptions.BrandNotFoundException;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentacar.entities.concretes.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {

    private BrandDao brandDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public BrandManager(BrandDao brandDao,ModelMapperService modelMapperService) {
        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<BrandListDto>> getAll() throws BusinessException {
        List<Brand> result = this.brandDao.findAll();
        
        if(result.isEmpty()) {
        	throw new BrandNotFoundException("Listede henüz bir marka yok.");
        }
        
        List<BrandListDto> response = result.stream()
                .map(brand -> this.modelMapperService
                        .forDto()
                        .map(brand,BrandListDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<BrandListDto>>(response, "Veri Listelendi");
    }

    @Override
    public Result add(CreateBrandRequest createBrandRequest) throws BusinessException{
        if(this.brandDao.existsBrandByBrandName(createBrandRequest.getBrandName())) {
        	
        	throw new BrandAlreadyExistException("Bu isimde marka kayıtlı.");
        }else {
            Brand brand = this.modelMapperService
                    .forRequest().map(createBrandRequest,Brand.class);
            this.brandDao.save(brand);
            return new SuccessResult("Marka eklendi");
        }
    }

    @Override
    public DataResult<GetBrandDto> getByBrandId(Integer id) throws BusinessException{
        
        if(this.brandDao.existsById(id)) {
        	Brand foundBrand = brandDao.getById(id);
            GetBrandDto response = this.modelMapperService.forDto().map(foundBrand, GetBrandDto.class);
            return new SuccessDataResult<GetBrandDto>(response, "Marka getirildi.");
        }else {
        	throw new BrandNotFoundException("Bu id'de kayıtlı marka bulunamadı.");
        }
    }

    @Override
    public Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException{
        if (this.brandDao.findById(updateBrandRequest.getBrandId()).isPresent() &&
        		!this.brandDao.existsBrandByBrandName(updateBrandRequest.getBrandName())) {
        	Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
        	this.brandDao.save(brand);
            return new SuccessResult("Güncelleme başarılı");
        }else if(this.brandDao.existsBrandByBrandName(updateBrandRequest.getBrandName())){
        	throw new BrandAlreadyExistException("Bu isimde başka bir marka zaten var.");
        }else {
        	throw new BrandNotFoundException("Bu id'de kayıtlı marka bulunamadı.");
        }
    }

    @Override
    public Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException{
        if (this.brandDao.existsBrandById(deleteBrandRequest.getBrandId())) {
           this.brandDao.deleteById(deleteBrandRequest.getBrandId());
           return new SuccessResult("Marka silindi");
        }else {
            throw new BrandNotFoundException("Bu id'de kayıtlı marka bulunamadı.");
        }
    }
}
