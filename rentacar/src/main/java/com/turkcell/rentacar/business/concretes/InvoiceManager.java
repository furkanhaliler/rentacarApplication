package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.turkcell.rentacar.business.abstracts.InvoiceService;
import com.turkcell.rentacar.business.abstracts.OrderedServiceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.gets.GetInvoiceDto;
import com.turkcell.rentacar.business.dtos.lists.InvoiceListDto;
import com.turkcell.rentacar.business.requests.create.CreateInvoiceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteInvoiceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateInvoiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.InvoiceDao;
import com.turkcell.rentacar.entities.concretes.Invoice;
import com.turkcell.rentacar.entities.concretes.Rent;

@Service
public class InvoiceManager implements InvoiceService {

	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	private RentService rentService;
	private OrderedServiceService orderedServiceService;

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService, RentService rentService, 
			OrderedServiceService orderedServiceService) {

		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.rentService = rentService;
		this.orderedServiceService = orderedServiceService;
	}

	@Override
	public DataResult<List<InvoiceListDto>> getAll() throws BusinessException {

		List<Invoice> result = this.invoiceDao.findAll();

		List<InvoiceListDto> response = result.stream()
				.map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<InvoiceListDto>>(response, "Veriler başarıyla getirildi.");
	}

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) throws BusinessException {

		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
				
		calculateAndSetTotalPrice(createInvoiceRequest.getRentRentId() , invoice);
		setRentDates(createInvoiceRequest.getRentRentId(), invoice);
		
		this.invoiceDao.save(invoice);
		
		return new SuccessResult("Başarıyla eklendi.");
	}

	@Override
	public DataResult<GetInvoiceDto> getById(Integer id) throws BusinessException {

		checkIfInvoiceIdExists(id);

		Invoice invoice = this.invoiceDao.getById(id);

		GetInvoiceDto response = this.modelMapperService.forDto().map(invoice, GetInvoiceDto.class);

		return new SuccessDataResult<GetInvoiceDto>(response, "Veri başarıyla getirildi.");
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) throws BusinessException {

		checkIfInvoiceIdExists(updateInvoiceRequest.getInvoiceId());

		Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		
		calculateAndSetTotalPrice(updateInvoiceRequest.getRentRentId() , invoice);
		setRentDates(updateInvoiceRequest.getRentRentId(), invoice);

		this.invoiceDao.save(invoice);
		
		return new SuccessResult("Başarıyla eklendi.");
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) throws BusinessException {

		checkIfInvoiceIdExists(deleteInvoiceRequest.getInvoiceId());
		
		this.invoiceDao.deleteById(deleteInvoiceRequest.getInvoiceId());

		return new SuccessResult("Başarıyla silindi.");
	}


	@Override
	public DataResult<List<InvoiceListDto>> getByCustomerUserId(Integer id) {
		
		List<Invoice> result = this.invoiceDao.findByCustomerUserId(id);
		
		List<InvoiceListDto> response = result.stream().map(invoice-> this.modelMapperService
				.forDto().map(invoice, InvoiceListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<InvoiceListDto>>(response, "Müşteri kullanıcı numarasına göre faturalar listelendi.");
		
	}
	
	@Override
	public DataResult<List<InvoiceListDto>> findByCreationDateBetween(LocalDate startDate, LocalDate endDate) {
		
		List<Invoice> result = this.invoiceDao.findByCreationDateBetween(startDate, endDate);
		
		List<InvoiceListDto> response = result.stream().map(invoice-> this.modelMapperService.forDto().map(invoice, InvoiceListDto.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<InvoiceListDto>>(response, "Veriler başarıyla sıralandı.");
	}
		
	@Override
	public void checkIfInvoiceIdExists(Integer id) throws BusinessException {
		
		if(!this.invoiceDao.existsById(id)) {
			
			throw new BusinessException("Bu ID'de kayıtlı fatura bulunamadı.");
		}

	}
	
	public void calculateAndSetTotalPrice (int rentId, Invoice invoice) {
		
		double rentPrice = this.rentService.calculateRentPrice(rentId);
		double orderedServicePrice = this.orderedServiceService.calculateOrderedServicePrice(rentId);
		
		double totalInvoicePrice = rentPrice + orderedServicePrice;
		
		invoice.setTotalPrice(totalInvoicePrice);
	}
	
	public void setRentDates (int rentId, Invoice invoice) {
		
		invoice.setCreationDate(LocalDate.now());
		
		Rent rent = this.rentService.bringRentForDates(rentId);
		
		invoice.setRentStartDate(rent.getRentStartDate());
		
		invoice.setRentReturnDate(rent.getRentReturnDate());
		
		invoice.setTotalRentDay((int)ChronoUnit.DAYS.between(rent.getRentStartDate(), rent.getRentReturnDate()));
	}

	


}
