package com.turkcell.rentacar.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.api.models.CreateRentModel;
import com.turkcell.rentacar.api.models.EndRentWithExtraPaymentModel;
import com.turkcell.rentacar.api.models.UpdateRentModel;
import com.turkcell.rentacar.business.abstracts.TransactionalRentService;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentModels")
public class TransactionalRentController {

	private TransactionalRentService transactionalRentService;
		
	@Autowired
	public TransactionalRentController(TransactionalRentService transactionalRentService) {
		super();
		this.transactionalRentService = transactionalRentService;
	}

	@PostMapping("/createRent")
	Result createRent(@RequestBody @Valid CreateRentModel createRentModel) throws BusinessException{
		
		return this.transactionalRentService.createRent(createRentModel);
	}
	
	@PostMapping("/endRentWithExtraPayment")
	Result endRentWithExtraPayment(@RequestBody @Valid EndRentWithExtraPaymentModel endRentWithExtraPaymentModel) throws BusinessException{
		
		return this.transactionalRentService.endRentWithExtraPayment(endRentWithExtraPaymentModel);
	}
	
	@PutMapping("/updateRent")
	Result updateRent(@RequestBody @Valid UpdateRentModel updateRentModel) throws BusinessException{
		
		return this.transactionalRentService.updateRent(updateRentModel);
	}
}
