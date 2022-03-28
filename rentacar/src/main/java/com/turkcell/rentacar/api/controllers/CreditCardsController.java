package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CreditCardService;
import com.turkcell.rentacar.business.dtos.gets.GetCreditCardDto;
import com.turkcell.rentacar.business.dtos.lists.CreditCardListDto;
import com.turkcell.rentacar.business.requests.creditCard.CreateCreditCardRequest;
import com.turkcell.rentacar.business.requests.creditCard.DeleteCreditCardRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;



@RestController
@RequestMapping("/api/creditCards")
public class CreditCardsController {

	private CreditCardService creditCardService;
	
	
	@Autowired
	public CreditCardsController(CreditCardService creditCardService) {
		
		this.creditCardService = creditCardService;
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateCreditCardRequest createCreditCardRequest){
		
		return this.creditCardService.add(createCreditCardRequest);
	}
	
	@GetMapping("/getAll")
	DataResult<List<CreditCardListDto>> getAll(){
		
		return this.creditCardService.getAll();
	}

	@GetMapping("/getById/{id}")
	DataResult<GetCreditCardDto> getById(@RequestParam("id") Integer id) throws BusinessException{
		
		return this.creditCardService.getById(id);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteCreditCardRequest deleteCreditCardRequest) throws BusinessException{
		
		return this.creditCardService.delete(deleteCreditCardRequest);
	}
	
	@GetMapping("/getByCustomerUserId/{customerUserId}")
	DataResult<List<CreditCardListDto>> getByCustomerUserId (@RequestParam("customerUserId") int customerUserId) throws BusinessException{
		
		return this.creditCardService.getByCustomerUserId(customerUserId);
	}
	
	
}
