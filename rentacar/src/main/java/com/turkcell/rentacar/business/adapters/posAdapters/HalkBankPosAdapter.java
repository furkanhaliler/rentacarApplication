package com.turkcell.rentacar.business.adapters.posAdapters;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.PosService;
import com.turkcell.rentacar.business.outServices.HalkBankPosManager;
import com.turkcell.rentacar.business.requests.pos.CreatePosRequest;

@Service
public class HalkBankPosAdapter implements PosService {

	@Override
	public boolean pay(CreatePosRequest createPosServiceRequest) {

		HalkBankPosManager halkBankPosManager = new HalkBankPosManager();

		boolean posResult = halkBankPosManager.makePayment(createPosServiceRequest.getCreditCardNo(),
				createPosServiceRequest.getCreditCardHolder(), createPosServiceRequest.getCvv(),
				createPosServiceRequest.getExpirationMonth(), createPosServiceRequest.getExpirationYear(), 0);

		return posResult;
	}

}
