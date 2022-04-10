package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.requests.pos.CreatePosRequest;

public interface PosService {

	boolean pay (CreatePosRequest createPosServiceRequest, double paymentAmount);
}
