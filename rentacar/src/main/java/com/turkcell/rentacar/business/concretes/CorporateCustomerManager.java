package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.dtos.gets.GetCorporateCustomerDto;
import com.turkcell.rentacar.business.dtos.lists.CorporateCustomerListDto;
import com.turkcell.rentacar.business.requests.create.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CorporateCustomerDao;
import com.turkcell.rentacar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {

	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
	
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<CorporateCustomerListDto>> getAll() throws BusinessException {
	
		List<CorporateCustomer> result = this.corporateCustomerDao.findAll();
		
		List<CorporateCustomerListDto> response = result.stream().map(corporateCustomer -> this.modelMapperService
				.forDto().map(corporateCustomer, CorporateCustomerListDto.class)).collect(Collectors.toList());
				
		return new SuccessDataResult<List<CorporateCustomerListDto>>(response, "Veriler başarıyla sıralandı.");
	}

	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException {
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		
		this.corporateCustomerDao.save(corporateCustomer);
		
		return new SuccessResult("Başarıyla eklendi.");
	}

	@Override
	public DataResult<GetCorporateCustomerDto> getByUserId(Integer id) throws BusinessException {
		
		checkIfCorporateCustomerIdExists(id);
		
		CorporateCustomer corporateCustomer = this.corporateCustomerDao.getById(id);
		
		GetCorporateCustomerDto response = this.modelMapperService.forDto().map(corporateCustomer, GetCorporateCustomerDto.class);
		
		return new SuccessDataResult<GetCorporateCustomerDto>(response, "Veri başarıyla getirildi.");
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException {
		
		checkIfCorporateCustomerIdExists(updateCorporateCustomerRequest.getUserId());
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCorporateCustomerRequest, CorporateCustomer.class);
		
		this.corporateCustomerDao.save(corporateCustomer);
		
		return new SuccessResult("Başarıyla güncellendi.");
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException {
		
		checkIfCorporateCustomerIdExists(deleteCorporateCustomerRequest.getUserId());
		
		this.corporateCustomerDao.deleteById(deleteCorporateCustomerRequest.getUserId());
		
		return new SuccessResult("Başarıyla silindi.");
	}

	@Override
	public void checkIfCorporateCustomerIdExists(Integer id) throws BusinessException {
	
		if(!this.corporateCustomerDao.existsById(id)) {
			
			throw new BusinessException("Bu ID'de kayıtlı kurumsal müşteri bulunamadı.");
		}

	}

}
