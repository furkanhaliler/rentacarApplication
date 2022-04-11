package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.OrderedServiceService;
import com.turkcell.rentacar.business.dtos.orderedService.GetOrderedServiceDto;
import com.turkcell.rentacar.business.dtos.orderedService.OrderedServiceListDto;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;

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

	@GetMapping("/getByOrderedServiceId/{orderedServiceId}")
	DataResult<GetOrderedServiceDto> getByOrderedServiceId(@RequestParam("orderedServiceId") Integer id) throws BusinessException{
		
		return this.orderedServiceService.getByOrderedServiceId(id);
	}
	
	@GetMapping("/getByRentId/{rentId}")
	DataResult<List<OrderedServiceListDto>> getByRentId(@RequestParam("rentId") Integer id) throws BusinessException{
		
		return this.orderedServiceService.getByRentId(id);
	}
	
	
}

