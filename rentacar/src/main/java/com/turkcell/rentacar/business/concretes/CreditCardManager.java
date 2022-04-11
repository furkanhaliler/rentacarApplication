package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CreditCardService;
import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.creditCard.CreditCardListDto;
import com.turkcell.rentacar.business.dtos.creditCard.GetCreditCardDto;
import com.turkcell.rentacar.business.requests.creditCard.CreateCreditCardRequest;
import com.turkcell.rentacar.business.requests.creditCard.DeleteCreditCardRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.creditCard.CreditCardNotFoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CreditCardDao;
import com.turkcell.rentacar.entities.concretes.CreditCard;

@Service
public class CreditCardManager implements CreditCardService {
	
	private CreditCardDao creditCardDao;
	private ModelMapperService modelMapperService;
	private CustomerService customerService;

	@Autowired
	public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService, CustomerService customerService) {
		
		this.creditCardDao = creditCardDao;
		this.modelMapperService = modelMapperService;
		this.customerService = customerService;
	}

	@Override
	public Result add(CreateCreditCardRequest createCreditCardRequest) {
		
		CreditCard creditCard = this.modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
		
		creditCard.setCreditCardId(0);
		
		this.creditCardDao.save(creditCard);
		
		return new SuccessResult(BusinessMessages.CREDIT_CARD_ADDED);
	}

	@Override
	public DataResult<List<CreditCardListDto>> getAll(){
		
		List<CreditCard> result = this.creditCardDao.findAll();
		
		List<CreditCardListDto> response = result.stream().map(creditCard -> this.modelMapperService
				.forDto().map(creditCard, CreditCardListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CreditCardListDto>>(response, BusinessMessages.CREDIT_CARDS_LISTED);
	}

	@Override
	public DataResult<GetCreditCardDto> getById(Integer id) throws BusinessException {
		
		checkIfCreditCardIdExists(id);
		
		CreditCard creditCard = this.creditCardDao.getById(id);
		
		GetCreditCardDto getCreditCardDto = this.modelMapperService.forDto().map(creditCard, GetCreditCardDto.class);
		
		return new SuccessDataResult<GetCreditCardDto>(getCreditCardDto, BusinessMessages.CREDIT_CARD_FOUND_BY_ID);
			
	}

	@Override
	public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) throws BusinessException {
		
		checkIfCreditCardIdExists(deleteCreditCardRequest.getCreditCardId());
		
		this.creditCardDao.deleteById(deleteCreditCardRequest.getCreditCardId());
		
		return new SuccessResult(BusinessMessages.CREDIT_CARD_DELETED);
	}

	@Override
	public DataResult<List<CreditCardListDto>> getByCustomerUserId(int customerUserId) throws BusinessException {
		
		this.customerService.checkIfCustomerIdExists(customerUserId);
		
		List<CreditCard> result = this.creditCardDao.findByCustomerUserId(customerUserId);
		
		List<CreditCardListDto> response = result.stream().map(creditCard -> this.modelMapperService.forDto()
				.map(creditCard, CreditCardListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CreditCardListDto>>(response, BusinessMessages.CREDIT_CARDS_LISTED_BY_CUSTOMER_ID);
	}

	@Override
	public void checkIfCreditCardIdExists(int creditCardId) throws BusinessException {
		
		if(!this.creditCardDao.existsById(creditCardId)) {
			
			throw new CreditCardNotFoundException(BusinessMessages.CREDIT_CARD_NOT_FOUND);
		}
	}

}
