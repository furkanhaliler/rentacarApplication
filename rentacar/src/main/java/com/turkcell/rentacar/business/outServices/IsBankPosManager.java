package com.turkcell.rentacar.business.outServices;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.turkcell.rentacar.business.abstracts.PosService;
import com.turkcell.rentacar.business.requests.Pos.CreatePosRequest;

@Component
@Primary
public class IsBankPosManager{

	
	public boolean makePayment(String carNo, String holderName, String cvv, int exprationMonth, int exprationYear, double paymentAmount) {
		
		return true;
	}

}
