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

import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.dtos.gets.GetCorporateCustomerDto;
import com.turkcell.rentacar.business.dtos.lists.CorporateCustomerListDto;
import com.turkcell.rentacar.business.requests.CorporateCustomer.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.CorporateCustomer.DeleteCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.CorporateCustomer.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporateCustomers")
public class CorporateCustomersController {
	
	@Autowired
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
	
		this.corporateCustomerService = corporateCustomerService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<CorporateCustomerListDto>> getAll() throws BusinessException{
		
		return this.corporateCustomerService.getAll();
	}
	
	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException{
		
		return this.corporateCustomerService.add(createCorporateCustomerRequest);
	}
	
	@GetMapping("/getByUserId/{id}")
	DataResult<GetCorporateCustomerDto> getByUserId(@RequestParam("userId") Integer id) throws BusinessException{
		
		return this.corporateCustomerService.getByUserId(id);
	}

	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateCorporateCustomerRequest updateCorporateCustomerRequest) throws BusinessException{
					
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException{
		
		return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
	}

	
}
