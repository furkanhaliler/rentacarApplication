package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.gets.GetCustomerDto;
import com.turkcell.rentacar.business.dtos.lists.CustomerListDto;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {

	private CustomerService customerService;

	@Autowired
	public CustomersController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<CustomerListDto>> getAll() throws BusinessException{
		
		return this.customerService.getAll();
	}

	@GetMapping("getByUserId/{userId}")
	DataResult<GetCustomerDto> getByUserId(@RequestParam("userId") Integer id) throws BusinessException{
		
		return this.customerService.getByUserId(id);
	}
	
	
	
}
