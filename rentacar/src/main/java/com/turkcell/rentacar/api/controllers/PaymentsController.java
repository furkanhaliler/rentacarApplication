package com.turkcell.rentacar.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {
	
	private PaymentService paymentService;

	@Autowired
	public PaymentsController(PaymentService paymentService) {
		super();
		this.paymentService = paymentService;
	}

//	@PostMapping("/add")
//	Result add(@RequestBody @Valid CreatePaymentRequest createPosServiceRequest) throws BusinessException{
//		
//		return this.paymentService.add(createPosServiceRequest);
//	}
}
