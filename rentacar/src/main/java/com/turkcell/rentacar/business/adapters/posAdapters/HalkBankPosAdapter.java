package com.turkcell.rentacar.business.adapters.posAdapters;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.outServices.HalkBankPosManager;
import com.turkcell.rentacar.business.outServices.IsBankPosManager;
import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.core.business.abstracts.PosService;

@Service
public class HalkBankPosAdapter implements PosService {

	@Override
	public boolean pay(CreatePaymentRequest createPosServiceRequest) {

		HalkBankPosManager halkBankPosManager = new HalkBankPosManager();

		boolean posResult = halkBankPosManager.makePayment(createPosServiceRequest.getCardNo(),
				createPosServiceRequest.getCardHolder(), createPosServiceRequest.getCvv(),
				createPosServiceRequest.getExpirationMonth(), createPosServiceRequest.getExpirationYear(), 0);

		return posResult;
	}

}
