package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.OrderedServiceService;
import com.turkcell.rentacar.business.dtos.gets.GetOrderedServiceDto;
import com.turkcell.rentacar.business.dtos.lists.OrderedServiceListDto;
import com.turkcell.rentacar.business.requests.orderedService.DeleteOrderedServiceRequest;
import com.turkcell.rentacar.business.requests.orderedService.UpdateOrderedServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/orderedServices")
public class OrderedServicesController {

	private OrderedServiceService orderedServiceService;

	@Autowired
	public OrderedServicesController(OrderedServiceService orderedServiceService) {
	
		this.orderedServiceService = orderedServiceService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<OrderedServiceListDto>> getAll(){
		
		return this.orderedServiceService.getAll();
	}

//	@PostMapping("/add")
//	Result add(@RequestBody @Valid CreateOrderedServiceRequest createOrderedServiceRequest){
//		
//		return this.orderedServiceService.add(createOrderedServiceRequest);
//	}

	@GetMapping("/getByOrderedServiceId/{orderedServiceId}")
	DataResult<GetOrderedServiceDto> getByOrderedServiceId(@RequestParam("orderedServiceId") Integer id) throws BusinessException{
		
		return this.orderedServiceService.getByOrderedServiceId(id);
	}

	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateOrderedServiceRequest updateOrderedServiceRequest) throws BusinessException{
		
		return this.orderedServiceService.update(updateOrderedServiceRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteOrderedServiceRequest deleteOrderedServiceRequest) throws BusinessException{
		
		return this.orderedServiceService.delete(deleteOrderedServiceRequest);
	}
	
	@GetMapping("/getByRentId/{rentId}")
	DataResult<List<OrderedServiceListDto>> getByRentId(@RequestParam("rentId") Integer id) throws BusinessException{
		
		return this.orderedServiceService.getByRentId(id);
	}
	
	
}

