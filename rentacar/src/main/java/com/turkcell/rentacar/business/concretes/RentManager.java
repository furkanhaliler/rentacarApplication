package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.gets.GetRentDto;
import com.turkcell.rentacar.business.dtos.lists.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.RentDao;
import com.turkcell.rentacar.entities.concretes.Rent;

@Service
public class RentManager implements RentService {

	private RentDao rentDao;
	private ModelMapperService modelMapperService;
	private CarMaintenanceService carMaintenanceService;

	@Autowired
	public RentManager(RentDao rentDao, ModelMapperService modelMapperService,
			CarMaintenanceService carMaintenanceService) {

		this.rentDao = rentDao;
		this.modelMapperService = modelMapperService;
		this.carMaintenanceService = carMaintenanceService;
	}

	@Override
	public Result add(CreateRentRequest createRentRequest) throws BusinessException {

		Rent rent = this.modelMapperService.forRequest().map(createRentRequest, Rent.class);
		
		rent.setRentId(0);
		
		this.carMaintenanceService.checkIfCarIsInMaintenance(createRentRequest.getCarId());
		
		checkIfCarIsRented(createRentRequest.getCarId());
		
		this.rentDao.save(rent);
		
		return new SuccessResult("Kiralama başarıyla eklendi.");
	}

	@Override
	public Result update(UpdateRentRequest updateRentRequest) throws BusinessException {

		checkIfRentIdExists(updateRentRequest.getRentId());
		
		Rent rent = this.modelMapperService.forRequest().map(updateRentRequest, Rent.class);

		this.rentDao.save(rent);
		
		return new SuccessResult("Kiralama başarıyla güncellendi.");
	}

	@Override
	public Result delete(DeleteRentRequest deleteRentRequest) throws BusinessException {

		checkIfRentIdExists(deleteRentRequest.getRentId());
		
		this.rentDao.deleteById(deleteRentRequest.getRentId());
		
		return new SuccessResult("Kiralama başarıyla silindi.");
	}

	@Override
	public DataResult<List<RentListDto>> getAll() throws BusinessException {

		List<Rent> rents = this.rentDao.findAll();

		List<RentListDto> response = rents.stream().map(rent -> this.modelMapperService
				.forDto().map(rent, RentListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<RentListDto>>(response, "Veriler başarıyla listelendi");
	}

	@Override
	public DataResult<List<RentListDto>> getByCarId(int id) throws BusinessException {

		List<Rent> rents = this.rentDao.getAllByCarId(id);

		List<RentListDto> response = rents.stream().map(rent -> this.modelMapperService
				.forDto().map(rent, RentListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<RentListDto>>(response, "ID'ye göre listelendi.");
	}

	@Override
	public DataResult<GetRentDto> getByRentId(int id) throws BusinessException {
		
		checkIfRentIdExists(id);
		
		Rent rent = this.rentDao.getById(id);
		
		GetRentDto response = this.modelMapperService.forDto().map(rent, GetRentDto.class);
		
		return new SuccessDataResult<GetRentDto>(response, "ID'ye göre listelendi.");
	}

	@Override
	public void checkIfCarIsRented(int id) throws BusinessException {

		List<Rent> result = this.rentDao.getAllByCarId(id);

		List<RentListDto> response = result.stream()
				.map(rent -> this.modelMapperService.forDto().map(rent, RentListDto.class))
				.collect(Collectors.toList());

		for (RentListDto rent : response) {
			if (rent.getRentReturnDate() == null || LocalDate.now().isBefore(rent.getRentReturnDate())
					|| LocalDate.now().isEqual(rent.getRentReturnDate())) {
				throw new BusinessException("Araç şu anda kiradadır.");
			}
		}
	}

	@Override
	public void checkIfRentIdExists(Integer id) throws BusinessException {
		
		if(!this.rentDao.existsById(id)) {
			
			throw new BusinessException("Bu ID'de kayıtlı kiralama bulunamadı.");
		}
	}
	
	@Override
	public double calculateRentPrice(int rentId) {
		
		double differentCityPrice = 0;
		
		if(!(this.rentDao.getById(rentId).getRentCity()==this.rentDao.getById(rentId).getReturnCity())) {
			
			differentCityPrice =750;
		}
		
		long daysBetween = (ChronoUnit.DAYS.between(
				this.rentDao.getById(rentId).getRentStartDate(), this.rentDao.getById(rentId).getRentReturnDate()) + 1);
			
		double dailyPrice = this.rentDao.getById(rentId).getCar().getDailyPrice();
		
		double totalRentPrice = (daysBetween * dailyPrice) + differentCityPrice;
		
		return totalRentPrice;
	}

	@Override
	public Rent bringRentForAnything(int rentId) {
		
		return this.rentDao.getById(rentId);
	}
}
