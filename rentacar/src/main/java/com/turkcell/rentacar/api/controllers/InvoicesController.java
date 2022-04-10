package com.turkcell.rentacar.api.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.dtos.gets.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.lists.InvoiceListDto;
import com.turkcell.rentacar.business.requests.Invoice.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.Invoice.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {

	private InvoiceService invoiceService;
	
	@Autowired
	public InvoicesController(InvoiceService invoiceService) {
		
		this.invoiceService = invoiceService;
	}
	
	@GetMapping("/getAll")
	DataResult<List<InvoiceListDto>> getAll(){
		
		return this.invoiceService.getAll();
	}

	@GetMapping("/getById/{invoiceId}")
	DataResult<GetInvoiceDto> getById(@RequestParam("invoiceId") Integer id) throws BusinessException{
		
		return this.invoiceService.getById(id);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException{
		
		return this.invoiceService.delete(deleteInvoiceRequest);
	}
	
	@GetMapping("/getByCustomerUserId/{customerUserId}")
	DataResult<List<InvoiceListDto>> getByCustomerUserId(@RequestParam("customerUserId") Integer id) throws BusinessException{
		
		return this.invoiceService.getByCustomerUserId(id);
	}
	
	@GetMapping("/getByRentId/{rentId}")
	DataResult<List<InvoiceListDto>> getByRentId(@RequestParam("rentId") Integer id) throws BusinessException{
		
		return this.invoiceService.getByRentId(id);
	}

	@GetMapping(value = "/findByCreationDateBetween/{startDate}/{endDate}")
	DataResult<List<InvoiceListDto>> findByCreationDateBetween(
			@RequestParam("startDate")
		@DateTimeFormat(pattern = "yyyy-MM-dd")	LocalDate startDate, 
		@DateTimeFormat(pattern = "yyyy-MM-dd")	@RequestParam("endDate") LocalDate endDate){
		
		return this.invoiceService.findByCreationDateBetween(startDate, endDate);
	}
	
	
	
	
}
