package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.gets.GetPaymentDto;
import com.turkcell.rentacar.business.dtos.lists.PaymentListDto;
import com.turkcell.rentacar.business.requests.Pos.CreatePosRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

	private PaymentService paymentService;

	@Autowired
	public PaymentsController(PaymentService paymentService) {
		
		this.paymentService = paymentService;
	}

	@GetMapping("/getAll")
	DataResult<List<PaymentListDto>> getAll() throws BusinessException{
		
		return this.paymentService.getAll();
	}

	@GetMapping("/getByPaymentId/{paymentId}")
	DataResult<GetPaymentDto> getByPaymentId(@RequestParam("paymentId") int paymentId) throws BusinessException{
		
		return this.paymentService.getByPaymentId(paymentId);
	}

	@GetMapping("/getByCustomerUserId/{customerUserId}")
	DataResult<List<PaymentListDto>> getByCustomerUserId(@RequestParam("customerUserId") int userId) throws BusinessException{
		
		return this.paymentService.getByCustomerUserId(userId);
	}

	@GetMapping("/getByInvoiceId/{invoiceId}")
	DataResult<GetPaymentDto> getByInvoiceId(@RequestParam("invoiceId") int invoiceId) throws BusinessException{
		
		return this.paymentService.getByInvoiceId(invoiceId);
	}

	@GetMapping("/getByRentId/{rentId}")
	DataResult<List<PaymentListDto>> getByRentId(@RequestParam("rentId") int rentId) throws BusinessException{
		
		return this.paymentService.getByRentId(rentId);
	}

}
