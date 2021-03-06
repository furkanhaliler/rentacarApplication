package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.additionalService.AdditionalServiceListDto;
import com.turkcell.rentacar.business.dtos.additionalService.GetAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.additionalService.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.additionalService.DeleteAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.additionalService.UpdateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.orderedService.CreateOrderedServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.additionalService.AdditionalServiceNameAlreadyExistsException;
import com.turkcell.rentacar.core.exceptions.additionalService.AdditionalServiceNotFoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.AdditionalServiceDao;
import com.turkcell.rentacar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {
	
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public AdditionalServiceManager(AdditionalServiceDao additionalServiceDao, ModelMapperService modelMapperService) {

		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<AdditionalServiceListDto>> getAll() {
		
		List<AdditionalService> result = this.additionalServiceDao.findAll();
		
		List<AdditionalServiceListDto> response = result.stream().map(additionalService -> this.modelMapperService
				.forDto().map(additionalService, AdditionalServiceListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<AdditionalServiceListDto>>
		(response, BusinessMessages.ADDITIONAL_SERVICES_LISTED);
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException {
		
		checkIfAdditionalServiceNameExists(createAdditionalServiceRequest.getAdditionalServiceName());
		
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest, AdditionalService.class);
		
		additionalService.setId(0);
		
		this.additionalServiceDao.save(additionalService);
		
		return new SuccessResult(BusinessMessages.ADDITIONAL_SERVICE_ADDED);	
	}

	@Override
	public DataResult<GetAdditionalServiceDto> getByAdditionalServiceId(Integer id) throws BusinessException {
		
		checkIfAdditionalServiceIdExists(id);
		
		AdditionalService additionalService = additionalServiceDao.getById(id);
		
		GetAdditionalServiceDto response = this.modelMapperService.forDto().map(additionalService, GetAdditionalServiceDto.class);
		
		return new SuccessDataResult<GetAdditionalServiceDto>(response, BusinessMessages.ADDITIONAL_SERVICE_FOUND_BY_ID);
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException {
	
		checkIfAdditionalServiceIdExists(updateAdditionalServiceRequest.getAdditionalServiceId());
		
		AdditionalService additionalService = this.modelMapperService.forRequest().map(updateAdditionalServiceRequest, AdditionalService.class);
		
		this.additionalServiceDao.save(additionalService);
		
		return new SuccessResult(BusinessMessages.ADDITIONAL_SERVICE_UPDATED);
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException {
		
		checkIfAdditionalServiceIdExists(deleteAdditionalServiceRequest.getAdditionalServiceId());
		
		this.additionalServiceDao.deleteById(deleteAdditionalServiceRequest.getAdditionalServiceId());
		
		return new SuccessResult(BusinessMessages.ADDITIONAL_SERVICE_DELETED);
	}

	@Override
	public void checkIfAdditionalServiceNameExists(String additionalServiceName) throws BusinessException {
		
		if(this.additionalServiceDao.existsAdditionalServiceByAdditionalServiceNameIgnoreCase(additionalServiceName)) {
			
			throw new AdditionalServiceNameAlreadyExistsException(BusinessMessages.ADDITIONAL_SERVICE_NAME_EXISTS);
		}
	}

	@Override
	public void checkIfAdditionalServiceIdExists(Integer id) throws BusinessException {
		
		if(!this.additionalServiceDao.existsById(id)) {
			
			throw new AdditionalServiceNotFoundException(BusinessMessages.ADDITIONAL_SERVICE_NOT_FOUND);
		}
	}
	
	@Override
	public void checkIfAdditionalServiceIdExistsOnOrderedServiceList(List<CreateOrderedServiceRequest> listOfCreateOrderedServiceRequests) 
			throws BusinessException {
		
		if(listOfCreateOrderedServiceRequests == null) {
			
			return;
		}
		
		for (CreateOrderedServiceRequest createOrderedServiceRequest : listOfCreateOrderedServiceRequests) {
			
			if(!this.additionalServiceDao.existsById(createOrderedServiceRequest.getAdditionalServiceId())) {
				
				throw new AdditionalServiceNotFoundException(BusinessMessages.ADDITIONAL_SERVICE_NOT_FOUND);
			}
		}
	}

	@Override
	public AdditionalService getById(int id) {

		return this.additionalServiceDao.getById(id);
	}
}
