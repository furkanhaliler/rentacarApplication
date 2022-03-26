package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentacar.business.dtos.gets.GetIndividualCustomerDto;
import com.turkcell.rentacar.business.dtos.lists.IndividualCustomerListDto;
import com.turkcell.rentacar.business.requests.IndividualCustomer.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.IndividualCustomer.DeleteIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.IndividualCustomer.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualCustomers")
public class IndividualCustomersController {

	@Autowired
	private IndividualCustomerService individualCustomerService;
	
	@GetMapping("/getAll")
	DataResult<List<IndividualCustomerListDto>> getAll() throws BusinessException{
		
		return this.individualCustomerService.getAll();
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException{
		
		return this.individualCustomerService.add(createIndividualCustomerRequest);
	}

	@GetMapping("/getByUserId/{id}")
	DataResult<GetIndividualCustomerDto> getByUserId(@RequestParam("userId") Integer id) throws BusinessException{
		
		return this.individualCustomerService.getByUserId(id);
	}

	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) throws BusinessException{
		
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException{
		
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
	}

}
