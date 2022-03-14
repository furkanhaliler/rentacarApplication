package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CityService;
import com.turkcell.rentacar.business.dtos.gets.GetCityDto;
import com.turkcell.rentacar.business.dtos.lists.CityListDto;
import com.turkcell.rentacar.business.requests.create.CreateCityRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCityRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCityRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CityDao;
import com.turkcell.rentacar.entities.concretes.City;

@Service
public class CityManager implements CityService {
	
	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
	
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CityListDto>> getAll() throws BusinessException {
		
		List<City> result = this.cityDao.findAll();
		
		List<CityListDto> response = result.stream().map(city -> this.modelMapperService
				.forDto().map(city, CityListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CityListDto>>(response, "Veriler başarıyla listelendi.");
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) throws BusinessException {
		
		checkIfCityNameExists(createCityRequest.getCityName());
		
		City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
		
		this.cityDao.save(city);
		
		return new SuccessResult("Başarıyla eklendi.");
	}

	@Override
	public DataResult<GetCityDto> getById(Integer id) throws BusinessException {
		
		checkIfCityIdExists(id);
		
		City city = this.cityDao.getById(id);
		
		GetCityDto response = this.modelMapperService.forDto().map(city, GetCityDto.class);
		
		return new SuccessDataResult<GetCityDto>(response, "Veri başarıyla getirildi.");
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) throws BusinessException {
		
		checkIfCityIdExists(updateCityRequest.getCityId());
		checkIfCityNameExists(updateCityRequest.getCityName());
	
		City city = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
		
		this.cityDao.save(city);
		
		return new SuccessResult("Başarıyla güncellendi.");
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) throws BusinessException {
		
		checkIfCityIdExists(deleteCityRequest.getCityId());
		
		this.cityDao.deleteById(deleteCityRequest.getCityId());
		
		return new SuccessResult("Başarıyla silindi.");
	}

	@Override
	public void checkIfCityNameExists(String cityName) throws BusinessException {
		
		if(this.cityDao.existsCityByCityName(cityName)) {
			
			throw new BusinessException("Bu isimde şehir zaten mevcut.");
		}
	}

	@Override
	public void checkIfCityIdExists(Integer id) throws BusinessException {
	
		if(!this.cityDao.existsById(id)) {
			
			throw new BusinessException("Bu ID'de kayıtlı şehir bulunamadı.");
		}
	}

}
