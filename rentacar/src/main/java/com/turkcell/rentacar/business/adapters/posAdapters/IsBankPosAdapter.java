package com.turkcell.rentacar.business.adapters.posAdapters;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.outServices.IsBankPosManager;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.core.business.abstracts.PosService;

@Service
@Primary
public class IsBankPosAdapter implements PosService{

	@Override
	public boolean pay(CreatePaymentRequest createPosServiceRequest) {
		
		IsBankPosManager isBankPosManager = new IsBankPosManager();
		
		boolean posResult =  isBankPosManager.makePayment(createPosServiceRequest.getCardNo(), createPosServiceRequest.getCardHolder()
				, createPosServiceRequest.getCvv(), createPosServiceRequest.getExpirationMonth(), createPosServiceRequest.getExpirationYear(), 0);
		
		return posResult;
	}

}
