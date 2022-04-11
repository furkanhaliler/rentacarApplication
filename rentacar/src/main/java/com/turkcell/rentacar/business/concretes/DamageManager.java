package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.DamageService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.damage.DamageListDto;
import com.turkcell.rentacar.business.dtos.damage.GetDamageDto;
import com.turkcell.rentacar.business.requests.damage.CreateDamageRequest;
import com.turkcell.rentacar.business.requests.damage.DeleteDamageRequest;
import com.turkcell.rentacar.business.requests.damage.UpdateDamageRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.damage.DamageNotFoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.DamageDao;
import com.turkcell.rentacar.entities.concretes.Damage;

@Service
public class DamageManager implements DamageService {

	private DamageDao damageDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	
	@Autowired
	public DamageManager(DamageDao damageDao, ModelMapperService modelMapperService, CarService carService) {
		
		this.damageDao = damageDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
	}

	@Override
	public Result add(CreateDamageRequest createDamageRequest) throws BusinessException{
		
		this.carService.checkIfCarIdExists(createDamageRequest.getCarCarId());
		
		Damage damage = this.modelMapperService.forRequest().map(createDamageRequest, Damage.class);
		
		damage.setDamageId(0);
		
		this.damageDao.save(damage);
		
		return new SuccessResult(BusinessMessages.DAMAGE_ADDED);
	}

	@Override
	public DataResult<List<DamageListDto>> getAll(){
		
		List<Damage> result = this.damageDao.findAll();
		
		List<DamageListDto> response = result.stream().map(damage -> this.modelMapperService
				.forDto().map(damage, DamageListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<DamageListDto>>(response, BusinessMessages.DAMAGES_LISTED);
	}

	@Override
	public DataResult<GetDamageDto> getById(Integer id) throws BusinessException {
		
		checkIfDamageIdExists(id);
		
		Damage damage = this.damageDao.getById(id);
		
		GetDamageDto response = this.modelMapperService.forDto().map(damage, GetDamageDto.class);
		
		return new SuccessDataResult<GetDamageDto>(response, BusinessMessages.DAMAGE_FOUND_BY_ID);
	}

	@Override
	public Result update(UpdateDamageRequest updateDamageRequest) throws BusinessException {
		
		checkIfDamageIdExists(updateDamageRequest.getDamageId());
		
		Damage damage = this.damageDao.getById(updateDamageRequest.getDamageId());
		
		Damage updatedDamage = this.modelMapperService.forRequest().map(updateDamageRequest, Damage.class);
		
		updatedDamage.setCar(damage.getCar());
		
		this.damageDao.save(updatedDamage);
		
		return new SuccessResult(BusinessMessages.DAMAGE_UPDATED);
	}

	@Override
	public Result delete(DeleteDamageRequest deleteDamageRequest) throws BusinessException {
		
		checkIfDamageIdExists(deleteDamageRequest.getDamageId());
		
		this.damageDao.deleteById(deleteDamageRequest.getDamageId());
		
		return new SuccessResult(BusinessMessages.DAMAGE_DELETED);
	}

	@Override
	public DataResult<List<DamageListDto>> getByCarId(Integer carId) throws BusinessException {
		
		this.carService.checkIfCarIdExists(carId);
		
		List<Damage> result = this.damageDao.findByCarId(carId);
		
		List<DamageListDto> response = result.stream().map(damage-> this.modelMapperService
				.forDto().map(damage, DamageListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<DamageListDto>>(response, BusinessMessages.DAMAGES_LISTED_BY_CAR_ID);	
	}

	@Override
	public void checkIfDamageIdExists(Integer id) throws BusinessException {
		
		if(!this.damageDao.existsById(id)) {
			
			throw new DamageNotFoundException(BusinessMessages.DAMAGE_NOT_FOUND);
		}

	}

}
