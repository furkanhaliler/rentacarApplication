package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.gets.GetBrandDto;
import com.turkcell.rentacar.business.dtos.lists.BrandListDto;
import com.turkcell.rentacar.business.requests.create.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.update.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BrandAlreadyExistsException;
import com.turkcell.rentacar.core.exceptions.BrandNotFoundException;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentacar.entities.concretes.Brand;

@Service
public class BrandManager implements BrandService {

	private BrandDao brandDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public BrandManager(BrandDao brandDao, ModelMapperService modelMapperService) {
		
		this.brandDao = brandDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<BrandListDto>> getAll() throws BusinessException {

		List<Brand> result = this.brandDao.findAll();

		List<BrandListDto> response = result.stream().map(brand -> this.modelMapperService
		.forDto().map(brand, BrandListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<BrandListDto>>(response, "Veriler Listelendi");
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) throws BusinessException {

		checkIfBrandNameExists(createBrandRequest.getBrandName());

		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		
		this.brandDao.save(brand);
		
		return new SuccessResult("Marka eklendi");
	}

	@Override
	public DataResult<GetBrandDto> getByBrandId(Integer id) throws BusinessException {

		checkIfBrandIdExists(id);

		Brand foundBrand = brandDao.getById(id);
		
		GetBrandDto response = this.modelMapperService.forDto().map(foundBrand, GetBrandDto.class);
		
		return new SuccessDataResult<GetBrandDto>(response, "Marka getirildi.");
	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException {

		checkIfBrandIdExists(updateBrandRequest.getBrandId());
		checkIfBrandNameExists(updateBrandRequest.getBrandName());

		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult("Güncelleme başarılı");
	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException {

		checkIfBrandIdExists(deleteBrandRequest.getBrandId());
		
		this.brandDao.deleteById(deleteBrandRequest.getBrandId());
		return new SuccessResult("Marka silindi");
	}

	@Override
	public void checkIfBrandNameExists(String brandName) throws BusinessException {

		if (this.brandDao.existsBrandByBrandName(brandName)) {

			throw new BrandAlreadyExistsException("Bu isimde marka zaten mevcut.");
		}
	}

	@Override
	public void checkIfBrandIdExists(Integer id) throws BusinessException {

		if (!this.brandDao.existsById(id)) {

			throw new BrandNotFoundException("Bu ID'de kayıtlı marka bulunamadı.");
		}
	}

}
