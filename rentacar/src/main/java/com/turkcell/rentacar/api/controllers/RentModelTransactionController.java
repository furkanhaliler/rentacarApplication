package com.turkcell.rentacar.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.api.model.CreateRentModel;
import com.turkcell.rentacar.api.model.EndRentWithExtraPaymentModel;
import com.turkcell.rentacar.business.abstracts.RentModelTransactionService;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentModels")
public class RentModelTransactionController {

	private RentModelTransactionService rentModelTransactionService;
		
	@Autowired
	public RentModelTransactionController(RentModelTransactionService rentModelTransactionService) {
		super();
		this.rentModelTransactionService = rentModelTransactionService;
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateRentModel createRentModel) throws BusinessException{
		
		return this.rentModelTransactionService.add(createRentModel);
	}
	
	@PostMapping("/endRentWithExtraPayment")
	Result endRentWithExtraPayment(@RequestBody @Valid EndRentWithExtraPaymentModel endRentWithExtraPaymentModel) throws BusinessException{
		
		return this.rentModelTransactionService.endRentWithExtraPayment(endRentWithExtraPaymentModel);
	}
}
