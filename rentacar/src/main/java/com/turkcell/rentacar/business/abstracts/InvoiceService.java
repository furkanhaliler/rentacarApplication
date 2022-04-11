package com.turkcell.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.rentacar.business.dtos.invoice.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.invoice.InvoiceListDto;
import com.turkcell.rentacar.business.requests.Invoice.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.Invoice.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.Invoice.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entities.concretes.Invoice;

public interface InvoiceService {

	DataResult<List<InvoiceListDto>> getAll();

	DataResult<Invoice> addForIndividualCustomers(CreateInvoiceRequest createInvoiceRequest) throws BusinessException;
	
	DataResult<Invoice> addForCorporateCustomers(CreateInvoiceRequest createInvoiceRequest) throws BusinessException;
	
	DataResult<Invoice> addExtraInvoice(int rentId, double totalPrice) throws BusinessException;

	DataResult<GetInvoiceDto> getById(Integer id) throws BusinessException;

	Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException;

	Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException;
		
	DataResult<List<InvoiceListDto>> getByCustomerUserId(Integer id) throws BusinessException;
	
	DataResult<List<InvoiceListDto>> getByRentId(Integer id) throws BusinessException;
	
	DataResult<List<InvoiceListDto>> findByCreationDateBetween(LocalDate startDate, LocalDate endDate);
	
	void checkIfInvoiceIdExists (Integer id) throws BusinessException;
	
	double calculateTotalPriceForIndividualCustomers (int rentId) throws BusinessException;
	
	double calculateTotalPriceForCorporateCustomers (int rentId) throws BusinessException;
	
	void setInvoiceFields (int rentId, Invoice invoice) throws BusinessException;

	
}
