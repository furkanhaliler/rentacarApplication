package com.turkcell.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.rentacar.business.dtos.rent.GetRentDto;
import com.turkcell.rentacar.business.dtos.rent.RentListDto;
import com.turkcell.rentacar.business.requests.rent.CreateRentRequest;
import com.turkcell.rentacar.business.requests.rent.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.rent.EndRentRequest;
import com.turkcell.rentacar.business.requests.rent.UpdateRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entities.concretes.Rent;

public interface RentService {
	
	DataResult<List<RentListDto>> getAll();

	DataResult<Rent> add(CreateRentRequest createRentRequest) throws BusinessException;

	Result update(UpdateRentRequest updateRentRequest) throws BusinessException;

	Result delete(DeleteRentRequest deleteRentRequest) throws BusinessException;

	DataResult<List<RentListDto>> getByCarId(int id) throws BusinessException;
	
	DataResult<GetRentDto> getByRentId(int id) throws BusinessException;
	
	Result endRent(EndRentRequest endRentRequest) throws BusinessException;

	void checkIfCarIsRented(int id) throws BusinessException;
	
	void checkIfRentIdExists (Integer id) throws BusinessException;
	
	double calculateRentPrice(int rentId);
	
	Rent bringRentById(int rentId) throws BusinessException;
	
	public void checkIfReturnDateDelayed (Rent rent) throws BusinessException;

	double calculateExtraDaysPrice(int rentId, LocalDate date) throws BusinessException;

	void checkIfRentAlreadyEnded(Rent rent) throws BusinessException;
	
	void checkIfDatesAreCorrect(LocalDate rentDate, LocalDate returnDate, int key) throws BusinessException;
	
	void checkIfEndKilometerIsCorrect(double startKilometer, double endKilometer) throws BusinessException;
	
}
