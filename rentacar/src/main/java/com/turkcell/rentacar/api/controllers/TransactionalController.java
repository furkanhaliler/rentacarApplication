package com.turkcell.rentacar.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.api.models.CreateRentModelForCorporateCustomers;
import com.turkcell.rentacar.api.models.CreateRentModelForIndividualCustomers;
import com.turkcell.rentacar.api.models.EndRentWithExtraPaymentModel;
import com.turkcell.rentacar.api.models.UpdateRentModel;
import com.turkcell.rentacar.business.abstracts.TransactionalService;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentModels")
public class TransactionalController {

	private TransactionalService transactionalService;
		
	@Autowired
	public TransactionalController(TransactionalService transactionalRentService) {
	
		this.transactionalService = transactionalRentService;
	}

	@PostMapping("/createRentForIndividualCustomers")
	Result createRentForIndividualCustomers(@RequestBody @Valid CreateRentModelForIndividualCustomers createRentModelForIndividualCustomers) 
			throws BusinessException{
		
		return this.transactionalService.createRentForIndividualCustomers(createRentModelForIndividualCustomers);
	}
	
	@PostMapping("/createRentForCorporateCustomers")
	Result createRentForCorporateCustomers(@RequestBody @Valid CreateRentModelForCorporateCustomers createRentModelForCorporateCustomers) 
			throws BusinessException{
		
		return this.transactionalService.createRentForCorporateCustomers(createRentModelForCorporateCustomers);
	}
	
	@PostMapping("/endRentWithExtraPayment")
	Result endRentWithExtraPayment(@RequestBody @Valid EndRentWithExtraPaymentModel endRentWithExtraPaymentModel) throws BusinessException{
		
		return this.transactionalService.endRentWithExtraPayment(endRentWithExtraPaymentModel);
	}
	
	@PutMapping("/updateRent")
	Result updateRent(@RequestBody @Valid UpdateRentModel updateRentModel) throws BusinessException{
		
		return this.transactionalService.updateRent(updateRentModel);
	}
}
