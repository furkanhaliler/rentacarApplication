package com.turkcell.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.turkcell.rentacar.business.dtos.CarListDto;
import com.turkcell.rentacar.business.dtos.GetCarDto;
import com.turkcell.rentacar.business.dtos.GetRentDto;
import com.turkcell.rentacar.business.dtos.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateCarRequest;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface RentService {

	Result add(CreateRentRequest createRentRequest) throws BusinessException;
	Result update(UpdateRentRequest updateRentRequest);
	Result delete(DeleteRentRequest deleteRentRequest);
	DataResult<List<RentListDto>> getAll() throws BusinessException;
	DataResult<List<RentListDto>> getByCarId(int id) throws BusinessException;
	boolean checkIfCarIsRented(int id);
	
}
