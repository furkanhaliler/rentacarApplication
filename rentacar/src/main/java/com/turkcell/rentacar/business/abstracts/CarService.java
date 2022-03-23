package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.gets.GetCarDto;
import com.turkcell.rentacar.business.dtos.lists.CarListDto;
import com.turkcell.rentacar.business.requests.create.CreateCarRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entities.concretes.Car;

import java.util.List;

public interface CarService {

	Result add(CreateCarRequest createCarRequest) throws BusinessException;

	DataResult<GetCarDto> getByCarId(int id) throws BusinessException;

	DataResult<List<CarListDto>> getAll() throws BusinessException;

	Result update(UpdateCarRequest updateCarRequest) throws BusinessException;

	Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException;

	DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize);

	DataResult<List<CarListDto>> getAllSorted(String orderOfSort) throws BusinessException;

	DataResult<List<CarListDto>> findByDailyPriceLessThan(double requestedPrice);

	DataResult<List<CarListDto>> findByDailyPriceGreaterThan(double requestedPrice);

	DataResult<List<CarListDto>> findByDailyPriceBetween(double minValue, double maxValue) throws BusinessException;

	void checkIfCarIdExists (Integer id) throws BusinessException;

	void setCarKilometer(Integer id, double kilometer);
	
	Car getCarByCarId(int carId);
}
