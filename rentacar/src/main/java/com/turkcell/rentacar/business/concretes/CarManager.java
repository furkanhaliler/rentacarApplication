package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.CityService;
import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.car.CarListDto;
import com.turkcell.rentacar.business.dtos.car.GetCarDto;
import com.turkcell.rentacar.business.requests.car.CreateCarRequest;
import com.turkcell.rentacar.business.requests.car.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.car.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.car.CarNotFoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CarDao;
import com.turkcell.rentacar.entities.concretes.Car;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private ModelMapperService modelMapperService;
	private BrandService brandService;
	private ColorService colorService;
	private CityService cityService;


	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService, BrandService brandService,
			ColorService colorService, CityService cityService) {
		
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.brandService = brandService;
		this.colorService = colorService;
		this.cityService = cityService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) throws BusinessException {
		
		this.brandService.checkIfBrandIdExists(createCarRequest.getBrandId());
		this.colorService.checkIfColorIdExists(createCarRequest.getColorId());
		this.cityService.checkIfCityIdExists(createCarRequest.getBaseCityId());

		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		
		car.setId(0);
		
		this.carDao.save(car);
		
		return new SuccessResult(BusinessMessages.CAR_ADDED);
	}


	@Override
	public DataResult<List<CarListDto>> getAll(){

		List<Car> result = this.carDao.findAll();

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarListDto>>(response, BusinessMessages.CARS_LISTED);
	}
	
	@Override
	public DataResult<GetCarDto> getByCarId(int id) throws BusinessException {

		checkIfCarIdExists(id);

		Car car = carDao.getById(id);
		
		GetCarDto response = this.modelMapperService.forDto().map(car, GetCarDto.class);

		return new SuccessDataResult<GetCarDto>(response, BusinessMessages.CAR_FOUND_BY_ID);
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) throws BusinessException {

		checkIfCarIdExists(updateCarRequest.getId());
		this.brandService.checkIfBrandIdExists(updateCarRequest.getBrandId());
		this.colorService.checkIfColorIdExists(updateCarRequest.getColorId());
		this.cityService.checkIfCityIdExists(updateCarRequest.getBaseCityId());
		
		Car car = this.carDao.getById(updateCarRequest.getId());
		
		Car updatedCar = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		
		updatedCar.setMaintenanceStatus(car.isMaintenanceStatus());
		updatedCar.setRentStatus(car.isRentStatus());
		updatedCar.setKilometer(car.getKilometer());
		
		this.carDao.save(updatedCar);

		return new SuccessResult(BusinessMessages.CAR_UPDATED);
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException {

		checkIfCarIdExists(deleteCarRequest.getId());

		this.carDao.deleteById(deleteCarRequest.getId());
		
		return new SuccessResult(BusinessMessages.CAR_DELETED);
	}

	@Override
	public DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize) throws BusinessException {

		if(pageNo < 1 || pageSize < 1) {
			
			throw new BusinessException(BusinessMessages.INVALID_PAGE_PARAM);
		}
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		List<Car> result = this.carDao.findAll(pageable).getContent();
		
		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response, BusinessMessages.CARS_PAGED);
	}

	@Override
	public DataResult<List<CarListDto>> getAllSorted(String param) throws BusinessException {

		Sort sort;

		if (param.toUpperCase().equals("ASC")) {
			sort = Sort.by(Sort.Direction.ASC, "dailyPrice");
		} else if (param.toUpperCase().equals("DESC")) {
			sort = Sort.by(Sort.Direction.DESC, "dailyPrice");
		} else {
			throw new BusinessException(BusinessMessages.INVALID_SORT_PARAM);
		}

		List<Car> result = this.carDao.findAll(sort);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response, BusinessMessages.CARS_SORTED);
	}

	@Override
	public DataResult<List<CarListDto>> findByDailyPriceLessThan(double requestedPrice) {

		List<Car> result = this.carDao.findByDailyPriceLessThan(requestedPrice);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response, BusinessMessages.CARS_LISTED);
	}

	@Override
	public DataResult<List<CarListDto>> findByDailyPriceGreaterThan(double requestedPrice) {

		List<Car> result = this.carDao.findByDailyPriceGreaterThan(requestedPrice);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response, BusinessMessages.CARS_LISTED);
	}

	@Override
	public DataResult<List<CarListDto>> findByDailyPriceBetween(double minValue, double maxValue){

		if (minValue > maxValue) {
			double a = minValue;
			minValue = maxValue;
			maxValue = a;
		}
		
		List<Car> result = this.carDao.findByDailyPriceBetween(minValue, maxValue);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response, BusinessMessages.CARS_LISTED);
	}

	@Override
	public void checkIfCarIdExists(Integer id) throws BusinessException {

		if (!this.carDao.existsById(id)) {

			throw new CarNotFoundException(BusinessMessages.CAR_NOT_FOUND);
		}
	}

	@Override
	public void setCarKilometer(Integer id, double kilometer) {
		
		Car car = this.carDao.getById(id);
		
		car.setKilometer(kilometer);
		
		this.carDao.save(car);
	}

	@Override
	public Car getCarByCarId(int carId) {
		
		Car car = this.carDao.getById(carId);
		
		return car;
	}

	@Override
	public void updateRentStatus(int carId, boolean status) {
		
		Car car = this.carDao.getById(carId);
		
		car.setRentStatus(status);
		
		this.carDao.save(car);		
	}

	@Override
	public void updateMaintenanceStatus(int carId, boolean status) {
		
		Car car = this.carDao.getById(carId);
		
		car.setMaintenanceStatus(status);
		
		this.carDao.save(car);	
	}

}
