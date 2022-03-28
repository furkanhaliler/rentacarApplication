package com.turkcell.rentacar.business.outServices;

import org.springframework.stereotype.Component;

@Component
public class HalkBankPosManager{

	
	public boolean makePayment(String carNo, String holderName, String cvv, int exprationMonth, int exprationYear, double paymentAmount) {
		
		return false;
	}
}
