package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.gets.GetRentDto;
import com.turkcell.rentacar.business.dtos.lists.RentListDto;
import com.turkcell.rentacar.business.requests.rent.CreateRentRequest;
import com.turkcell.rentacar.business.requests.rent.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.rent.EndRentRequest;
import com.turkcell.rentacar.business.requests.rent.UpdateRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.rent.CarIsCurrentlyRentedException;
import com.turkcell.rentacar.core.exceptions.rent.RentNotFoundException;
import com.turkcell.rentacar.core.exceptions.rent.RentReturnDateDelayedException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.RentDao;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.Rent;

@Service
public class RentManager implements RentService {

	private RentDao rentDao;
	private ModelMapperService modelMapperService;
	private CarMaintenanceService carMaintenanceService;
	private CarService carService;


	@Autowired
	public RentManager(RentDao rentDao, ModelMapperService modelMapperService,
			CarMaintenanceService carMaintenanceService, CarService carService) {

		this.rentDao = rentDao;
		this.modelMapperService = modelMapperService;
		this.carMaintenanceService = carMaintenanceService;
		this.carService = carService;

	}

	@Override
	public DataResult<List<RentListDto>> getAll(){

		List<Rent> rents = this.rentDao.findAll();

		List<RentListDto> response = rents.stream().map(rent -> this.modelMapperService
				.forDto().map(rent, RentListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<RentListDto>>(response, BusinessMessages.RENTS_LISTED);
	}
	
	@Override
	public DataResult<Rent> add(CreateRentRequest createRentRequest) throws BusinessException {
		
		this.carMaintenanceService.checkIfCarIsInMaintenance(createRentRequest.getCarId());
		
		checkIfCarIsRented(createRentRequest.getCarId());

		Rent rent = this.modelMapperService.forRequest().map(createRentRequest, Rent.class);
		
		rent.setRentId(0);
		
		rent.setStartKilometer(carService.getByCarId(createRentRequest.getCarId()).getData().getKilometer());	
		
		Rent savedRent = this.rentDao.save(rent);
		
		return new SuccessDataResult<Rent>(savedRent, BusinessMessages.RENT_ADDED);
	}

	
	@Override
	public DataResult<GetRentDto> getByRentId(int id) throws BusinessException {
		
		checkIfRentIdExists(id);
		
		Rent rent = this.rentDao.getById(id);
		
		GetRentDto response = this.modelMapperService.forDto().map(rent, GetRentDto.class);
		
		return new SuccessDataResult<GetRentDto>(response, BusinessMessages.RENT_FOUND_BY_ID);
	}
	
	
	@Override
	public Result update(UpdateRentRequest updateRentRequest) throws BusinessException {

		checkIfRentIdExists(updateRentRequest.getRentId());
		
		Rent rent = this.modelMapperService.forRequest().map(updateRentRequest, Rent.class);

		this.rentDao.save(rent);
		
		return new SuccessResult(BusinessMessages.RENT_UPDATED);
	}

	@Override
	public Result delete(DeleteRentRequest deleteRentRequest) throws BusinessException {

		checkIfRentIdExists(deleteRentRequest.getRentId());
		
		this.rentDao.deleteById(deleteRentRequest.getRentId());
		
		return new SuccessResult(BusinessMessages.RENT_DELETED);
	}
	

	@Override
	public DataResult<List<RentListDto>> getByCarId(int id) throws BusinessException {
		
		this.carService.checkIfCarIdExists(id);

		List<Rent> rents = this.rentDao.getAllByCarId(id);

		List<RentListDto> response = rents.stream().map(rent -> this.modelMapperService
				.forDto().map(rent, RentListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<RentListDto>>(response, BusinessMessages.RENTS_LISTED_BY_CAR_ID);
	}

	
	@Override
	public Result endRent(EndRentRequest endRentRequest) throws BusinessException {
		
		checkIfRentIdExists(endRentRequest.getRentId());
		
		Rent rent = this.rentDao.getById(endRentRequest.getRentId());
		
		checkIfReturnDateDelayed(rent);
		
		rent.setRentReturnDate(LocalDate.now());
		rent.setEndKilometer(endRentRequest.getEndKilometer());
		
		this.rentDao.save(rent);
		
		carService.setCarKilometer(rent.getCar().getId(), endRentRequest.getEndKilometer());
		
		return new SuccessResult(BusinessMessages.RENT_ENDED);
		
	}

	@Override
	public void checkIfCarIsRented(int id) throws BusinessException {

		List<Rent> result = this.rentDao.getAllByCarId(id);

		for (Rent rent : result) {
			if (rent.getRentReturnDate() == null || LocalDate.now().isBefore(rent.getRentReturnDate())
					|| LocalDate.now().isEqual(rent.getRentReturnDate())) {
				
				throw new CarIsCurrentlyRentedException(BusinessMessages.CAR_IS_CURRENTLY_RENTED);
			}
		}
	}

	@Override
	public void checkIfRentIdExists(Integer id) throws BusinessException {
		
		if(!this.rentDao.existsById(id)) {
			
			throw new RentNotFoundException(BusinessMessages.RENT_NOT_FOUND);
		}
	}
	
	@Override
	public double calculateRentPrice(int rentId) {
		
		double differentCityPrice = 0;
		
		if(!(this.rentDao.getById(rentId).getRentCity().equals(this.rentDao.getById(rentId).getReturnCity()))) {
			
			differentCityPrice = 750;
		}
		
		long daysBetween = (ChronoUnit.DAYS.between(
				this.rentDao.getById(rentId).getRentStartDate(), this.rentDao.getById(rentId).getRentReturnDate()) + 1);
		
		Car car = this.carService.getCarByCarId(this.rentDao.getById(rentId).getCar().getId());
		
		double dailyPrice = car.getDailyPrice();
		
		double totalRentPrice = (daysBetween * dailyPrice) + differentCityPrice;
		
		return totalRentPrice;
	}

	@Override
	public Rent bringRentById(int rentId) throws BusinessException{
		
		checkIfRentIdExists(rentId);
		
		return this.rentDao.getById(rentId);
	}

	@Override
	public void checkIfReturnDateDelayed (Rent rent) throws BusinessException {
		
		if(LocalDate.now().isAfter(rent.getRentReturnDate())){
			
			double extraPrice = calculateExtraDaysPrice(rent.getRentId());
			
			throw new RentReturnDateDelayedException(BusinessMessages.NEED_EXTRA_PAYMENT + extraPrice);
		}
	}
	
	@Override
	public double calculateExtraDaysPrice (int rentId) {
		
		Rent rent = this.rentDao.getById(rentId);
		
		long daysBetween = (ChronoUnit.DAYS.between(rent.getRentReturnDate(), LocalDate.now()));
		
		Car car = this.carService.getCarByCarId(rent.getCar().getId());
		
		double dailyPrice = car.getDailyPrice();
		
		double extraPrice = daysBetween * dailyPrice;
		
		return extraPrice;
	}
	
	
	
}
