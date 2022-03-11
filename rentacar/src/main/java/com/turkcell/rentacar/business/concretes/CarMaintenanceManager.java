package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.gets.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.lists.CarMaintenanceListDto;
import com.turkcell.rentacar.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentacar.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private RentService rentService;

	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,
			@Lazy RentService rentService) {

		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.rentService = rentService;
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getAll() throws BusinessException {

		List<CarMaintenance> result = this.carMaintenanceDao.findAll();

		List<CarMaintenanceListDto> response = result.stream().map(
				carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<CarMaintenanceListDto>>(response, "Veriler başarıyla listelendi.");
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException {

		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		carMaintenance.setMaintenanceId(0);;
		this.rentService.checkIfCarIsRented(createCarMaintenanceRequest.getCarId());
		this.carMaintenanceDao.save(carMaintenance);

		return new SuccessResult("Araba bakımı eklendi");
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getByCarId(Integer id) throws BusinessException {

		List<CarMaintenance> carMaintenanceList = carMaintenanceDao.getAllByCarId(id);

		List<CarMaintenanceListDto> response = carMaintenanceList.stream().map(
				carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response, "Id'ye göre listelendi.");
	}

	@Override
	public DataResult<GetCarMaintenanceDto> getByCarMaintenanceId(Integer id) throws BusinessException {
	
		checkIfCarMaintenanceIdExists(id);
		
		CarMaintenance carMaintenance = this.carMaintenanceDao.getById(id);
		
		GetCarMaintenanceDto response = this.modelMapperService.forDto().map(carMaintenance, GetCarMaintenanceDto.class);
		return new SuccessDataResult<GetCarMaintenanceDto>(response, "ID'ye göre listelendi.");
	}
	
	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {

		checkIfCarMaintenanceIdExists(updateCarMaintenanceRequest.getMaintenanceId());

		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest,
				CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		return new SuccessResult("Bakım güncellendi.");
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException {

		checkIfCarMaintenanceIdExists(deleteCarMaintenanceRequest.getMaintenanceId());
	
		this.carMaintenanceDao.deleteById(deleteCarMaintenanceRequest.getMaintenanceId());
		return new SuccessResult("Bakım silindi.");
	}

	@Override
	public void checkIfCarIsInMaintenance(int id) throws BusinessException {

		List<CarMaintenance> result = this.carMaintenanceDao.getAllByCarId(id);

		List<CarMaintenanceListDto> response = result.stream().map(
				carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class))
				.collect(Collectors.toList());

		for (CarMaintenanceListDto carMaintenance : response) {
			if ((carMaintenance.getReturnDate() == null) || LocalDate.now().isBefore(carMaintenance.getReturnDate())
					|| LocalDate.now().isEqual(carMaintenance.getReturnDate())) {

				throw new BusinessException("Araba şu anda bakımdadır.");
			}
		}
	}

	@Override
	public void checkIfCarMaintenanceIdExists(Integer id) throws BusinessException {

		if (!this.carMaintenanceDao.existsById(id)) {

			throw new BusinessException("Bu ID'de kayıtlı bakım bulunamadı.");
		}
	}


}