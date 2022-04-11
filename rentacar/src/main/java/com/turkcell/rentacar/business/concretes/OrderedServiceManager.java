package com.turkcell.rentacar.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.abstracts.OrderedServiceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.orderedService.GetOrderedServiceDto;
import com.turkcell.rentacar.business.dtos.orderedService.OrderedServiceListDto;
import com.turkcell.rentacar.business.requests.orderedService.CreateOrderedServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.orderedService.OrderedServiceNotFoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.OrderedServiceDao;
import com.turkcell.rentacar.entities.concretes.AdditionalService;
import com.turkcell.rentacar.entities.concretes.OrderedService;
import com.turkcell.rentacar.entities.concretes.Rent;

@Service
public class OrderedServiceManager implements OrderedServiceService {
	
	private OrderedServiceDao orderedServiceDao;
	private ModelMapperService modelMapperService;
	private RentService rentService;
	private AdditionalServiceService additionalServiceService;
	
	@Autowired
	public OrderedServiceManager(OrderedServiceDao orderedServiceDao, ModelMapperService modelMapperService, RentService rentService
			,AdditionalServiceService additionalServiceService) {
	
		this.orderedServiceDao = orderedServiceDao;
		this.modelMapperService = modelMapperService;
		this.rentService = rentService;
		this.additionalServiceService = additionalServiceService;
	}

	@Override
	public DataResult<List<OrderedServiceListDto>> getAll(){
		
		List<OrderedService> result = this.orderedServiceDao.findAll();
		
		List<OrderedServiceListDto> response = result.stream().map(orderedService -> this.modelMapperService
				.forDto().map(orderedService, OrderedServiceListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<OrderedServiceListDto>>(response, BusinessMessages.ORDERED_SERVICES_LISTED);
	}

	@Override
	public Result add(CreateOrderedServiceRequest createOrderedServiceRequest){
		
		OrderedService orderedService = this.modelMapperService.forRequest().map(createOrderedServiceRequest, OrderedService.class);
		
		orderedService.setId(0);
		
		this.orderedServiceDao.save(orderedService);
		
		return new SuccessResult(BusinessMessages.ORDERED_SERVICE_ADDED);
	}

	@Override
	public DataResult<GetOrderedServiceDto> getByOrderedServiceId(Integer id) throws BusinessException {
		
		checkIfOrderedServiceIdExists(id);
		
		OrderedService orderedService = this.orderedServiceDao.getById(id);
		
		GetOrderedServiceDto response = this.modelMapperService.forDto().map(orderedService, GetOrderedServiceDto.class);
		
		return new SuccessDataResult<GetOrderedServiceDto>(response, BusinessMessages.ORDERED_SERVICE_FOUND_BY_ID);
	}

	@Override
	public DataResult<List<OrderedServiceListDto>> getByRentId(Integer id) throws BusinessException{
		
		this.rentService.checkIfRentIdExists(id);
		
		List<OrderedService> result = this.orderedServiceDao.findOrderedServicesByRent_RentId(id);
		
		List<OrderedServiceListDto> response = result.stream().map(orderedService -> this.modelMapperService
				.forDto().map(orderedService, OrderedServiceListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<OrderedServiceListDto>>(response, BusinessMessages.ORDERED_SERVICES_LISTED_BY_RENT_ID);
	}
	
	@Override
	public void checkIfOrderedServiceIdExists(Integer id) throws BusinessException {
		
		if(!this.orderedServiceDao.existsById(id)) {
			
			throw new OrderedServiceNotFoundException(BusinessMessages.ORDERED_SERVICE_NOT_FOUND);
		}
	}
	
	@Override
	public double calculateOrderedServicePrice(int rentId) throws BusinessException{
		
		List<OrderedService> result = this.orderedServiceDao.findOrderedServicesByRent_RentId(rentId);
		
		double totalPrice = 0;
		
		for (OrderedService orderedService : result) {
			
			AdditionalService additionalService = this.additionalServiceService.getById(orderedService.getAdditionalService().getId());
			
			totalPrice += orderedService.getOrderedServiceAmount() * additionalService.getDailyPrice();
		}
		
		Rent rent = this.rentService.bringRentById(rentId);
		
		long daysBetween = (ChronoUnit.DAYS.between(rent.getRentStartDate(), rent.getRentReturnDate()) +1);
		
		return totalPrice * daysBetween;
	}


}
