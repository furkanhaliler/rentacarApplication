package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetRentDto;
import com.turkcell.rentacar.business.dtos.lists.RentListDto;
import com.turkcell.rentacar.business.requests.EndRentRequest;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entities.concretes.Rent;

public interface RentService {

	DataResult<Rent> add(CreateRentRequest createRentRequest) throws BusinessException;

	Result update(UpdateRentRequest updateRentRequest) throws BusinessException;

	Result delete(DeleteRentRequest deleteRentRequest) throws BusinessException;

	DataResult<List<RentListDto>> getAll() throws BusinessException;

	DataResult<List<RentListDto>> getByCarId(int id) throws BusinessException;
	
	DataResult<GetRentDto> getByRentId(int id) throws BusinessException;
	
	Result endRent(EndRentRequest endRentRequest) throws BusinessException;

	void checkIfCarIsRented(int id) throws BusinessException;
	
	void checkIfRentIdExists (Integer id) throws BusinessException;
	
	double calculateRentPrice(int rentId);
	
	Rent bringRentById(int rentId);
	
}
