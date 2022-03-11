package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.OrderedServiceService;
import com.turkcell.rentacar.business.dtos.gets.GetOrderedServiceDto;
import com.turkcell.rentacar.business.dtos.lists.OrderedServiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateOrderedServiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteOrderedServiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateOrderedServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.OrderedServiceDao;
import com.turkcell.rentacar.entities.concretes.OrderedService;

@Service
public class OrderedServiceManager implements OrderedServiceService {
	
	OrderedServiceDao orderedServiceDao;
	ModelMapperService modelMapperService;
	
	@Autowired
	public OrderedServiceManager(OrderedServiceDao orderedServiceDao, ModelMapperService modelMapperService) {
	
		this.orderedServiceDao = orderedServiceDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<OrderedServiceListDto>> getAll() throws BusinessException {
		
		List<OrderedService> result = this.orderedServiceDao.findAll();
		
		List<OrderedServiceListDto> response = result.stream().map(orderedService -> this.modelMapperService
				.forDto().map(orderedService, OrderedServiceListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<OrderedServiceListDto>>(response, "Veriler başarıyla listelendi.");
	}

	@Override
	public Result add(CreateOrderedServiceRequest createOrderedServiceRequest) throws BusinessException {
		
		OrderedService orderedService = this.modelMapperService.forRequest().map(createOrderedServiceRequest, OrderedService.class);
		orderedService.setId(0);
		this.orderedServiceDao.save(orderedService);
		
		return new SuccessResult("Başarıyla eklendi.");
	}

	@Override
	public DataResult<GetOrderedServiceDto> getByOrderedServiceId(Integer id) throws BusinessException {
		
		checkIfOrderedServiceIdExists(id);
		
		OrderedService orderedService = this.orderedServiceDao.getById(id);
		GetOrderedServiceDto response = this.modelMapperService.forDto().map(orderedService, GetOrderedServiceDto.class);
		
		return new SuccessDataResult<GetOrderedServiceDto>(response, "Veri başarıyla getirildi.");
	}

	@Override
	public Result update(UpdateOrderedServiceRequest updateOrderedServiceRequest) throws BusinessException {
		
		checkIfOrderedServiceIdExists(updateOrderedServiceRequest.getId());
		
		OrderedService orderedService = this.modelMapperService.forRequest().map(updateOrderedServiceRequest, OrderedService.class);
		this.orderedServiceDao.save(orderedService);
	
		return new SuccessResult("Başarıyla güncellendi.");
	}

	@Override
	public Result delete(DeleteOrderedServiceRequest deleteOrderedServiceRequest) throws BusinessException {
	
		checkIfOrderedServiceIdExists(deleteOrderedServiceRequest.getOrderedServiceId());
		
		this.orderedServiceDao.deleteById(deleteOrderedServiceRequest.getOrderedServiceId());
		
		return new SuccessResult("Başarıyla silindi.");
	}

	@Override
	public void checkIfOrderedServiceIdExists(Integer id) throws BusinessException {
		
		if(!this.orderedServiceDao.existsById(id)) {
			
			throw new BusinessException("Bu ID'de kayıtlı sipariş bulunamadı.");
		}

	}

}
