package com.turkcell.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.lists.InvoiceListDto;
import com.turkcell.rentacar.business.requests.Invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.Invoice.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.Invoice.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entities.concretes.Invoice;

public interface InvoiceService {

	DataResult<List<InvoiceListDto>> getAll() throws BusinessException;

	DataResult<Invoice> add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException;

	DataResult<GetInvoiceDto> getById(Integer id) throws BusinessException;

	Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException;

	Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException;
		
	DataResult<List<InvoiceListDto>> getByCustomerUserId(Integer id);
	
	DataResult<List<InvoiceListDto>> findByCreationDateBetween(LocalDate startDate, LocalDate endDate);
	
	void checkIfInvoiceIdExists (Integer id) throws BusinessException;
	
	double calculateTotalPrice (int rentId);
	
	void setInvoiceFields (int rentId, Invoice invoice);

	
}
