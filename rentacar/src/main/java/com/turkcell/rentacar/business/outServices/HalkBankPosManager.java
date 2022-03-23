package com.turkcell.rentacar.business.outServices;

import org.springframework.stereotype.Component;

import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;
import com.turkcell.rentacar.core.business.abstracts.PosService;

@Component
public class HalkBankPosManager{

	
	public boolean makePayment(String carNo, String holderName, String cvv, int exprationMonth, int exprationYear, double paymentAmount) {
		
		return false;
	}
}
