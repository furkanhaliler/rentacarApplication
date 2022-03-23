package com.turkcell.rentacar.api.model;

import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface RentModelService {

	Result add(CreateRentModel createRentModel) throws BusinessException;
}
