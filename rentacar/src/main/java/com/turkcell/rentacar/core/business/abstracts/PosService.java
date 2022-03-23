package com.turkcell.rentacar.core.business.abstracts;

import com.turkcell.rentacar.business.requests.create.CreatePaymentRequest;

public interface PosService {

	boolean pay (CreatePaymentRequest createPosServiceRequest);
}
