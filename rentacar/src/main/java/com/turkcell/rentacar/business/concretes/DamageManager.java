package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.DamageService;
import com.turkcell.rentacar.business.dtos.gets.GetDamageDto;
import com.turkcell.rentacar.business.dtos.lists.DamageListDto;
import com.turkcell.rentacar.business.requests.create.CreateDamageRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteDamageRequest;
import com.turkcell.rentacar.business.requests.update.UpdateDamageRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
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
	
	@Autowired
	public DamageManager(DamageDao damageDao, ModelMapperService modelMapperService) {
		
		this.damageDao = damageDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateDamageRequest createDamageRequest) throws BusinessException {
		
		Damage damage = this.modelMapperService.forRequest().map(createDamageRequest, Damage.class);
		damage.setDamageId(0);
		
		this.damageDao.save(damage);
		
		return new SuccessResult("Hasar kaydı başarıyla eklendi.");
	}

	@Override
	public DataResult<List<DamageListDto>> getAll() throws BusinessException {
		
		List<Damage> result = this.damageDao.findAll();
		
		List<DamageListDto> response = result.stream().map(damage -> this.modelMapperService
				.forDto().map(damage, DamageListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<DamageListDto>>(response, "Veriler başarıyla listelendi.");
	}

	@Override
	public DataResult<GetDamageDto> getById(Integer id) throws BusinessException {
		
		checkIfDamageIdExists(id);
		
		Damage damage = this.damageDao.getById(id);
		
		GetDamageDto response = this.modelMapperService.forDto().map(damage, GetDamageDto.class);
		
		return new SuccessDataResult<GetDamageDto>(response, "ID'ye göre başarıyla getirildi.");
	}

	@Override
	public Result update(UpdateDamageRequest updateDamageRequest) throws BusinessException {
		
		checkIfDamageIdExists(updateDamageRequest.getDamageId());
		
		Damage damage = this.modelMapperService.forRequest().map(updateDamageRequest, Damage.class);
		
		this.damageDao.save(damage);
		
		return new SuccessResult("Başarıyla güncellendi.");
	}

	@Override
	public Result delete(DeleteDamageRequest deleteDamageRequest) throws BusinessException {
		
		checkIfDamageIdExists(deleteDamageRequest.getDamageId());
		
		this.damageDao.deleteById(deleteDamageRequest.getDamageId());
		
		return new SuccessResult("Başarıyla silindi.");
	}

	@Override
	public DataResult<List<DamageListDto>> getByCarId(Integer carId) throws BusinessException {
		
		List<Damage> result = this.damageDao.findByCarId(carId);
		
		List<DamageListDto> response = result.stream().map(damage-> this.modelMapperService
				.forDto().map(damage, DamageListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<DamageListDto>>(response, "Car ID'ye göre başarıyla sıralandı.");	
	}

	@Override
	public void checkIfDamageIdExists(Integer id) throws BusinessException {
		
		if(!this.damageDao.existsById(id)) {
			
			throw new BusinessException("Bu ID'de kayıtlı hasar kaydı bulunamadı.");
		}

	}

}
