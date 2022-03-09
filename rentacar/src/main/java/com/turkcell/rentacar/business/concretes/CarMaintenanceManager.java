package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.*;
import com.turkcell.rentacar.business.requests.*;
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
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private RentService rentService;

	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,
			@Lazy CarService carService, @Lazy RentService rentService) {

		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.rentService = rentService;

	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getAll() throws BusinessException {

		List<CarMaintenance> result = this.carMaintenanceDao.findAll();
		if (result.isEmpty()) {
			throw new BusinessException("Listede hiç veri yok.");
		} else {
			List<CarMaintenanceListDto> response = result.stream().map(
					carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class))
					.collect(Collectors.toList());
			return new SuccessDataResult<List<CarMaintenanceListDto>>(response, "Veriler başarıyla listelendi.");
		}

	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException {

		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest,
				CarMaintenance.class);
		carMaintenance.setMaintenanceId(0);

		this.carService.updateRentStatus(createCarMaintenanceRequest.getCarId(),
				this.rentService.checkIfCarIsRented(createCarMaintenanceRequest.getCarId()));

		if (this.carService.getByCarId(createCarMaintenanceRequest.getCarId()).getData().isRentStatus()) {
			throw new BusinessException("Araç şu an kiradadır. Bakıma alınamaz.");
		} else {
			this.carMaintenanceDao.save(carMaintenance);
			this.carService.updateCarMaintenanceStatus(createCarMaintenanceRequest.getCarId(), true);

			return new SuccessResult("Araba bakımı eklendi");
		}
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getByCarId(Integer id) throws BusinessException {

		List<CarMaintenance> carMaintenanceList = carMaintenanceDao.getAllByCarId(id);
		if (carMaintenanceList.isEmpty()) {
			throw new BusinessException("Bu Id'de kayıtlı bir bakım bulunamadı.");
		} else {

			List<CarMaintenanceListDto> response = carMaintenanceList.stream().map(
					carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class))
					.collect(Collectors.toList());
			return new SuccessDataResult<List<CarMaintenanceListDto>>(response, "Id'ye göre listelendi.");
		}
	}

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException {

		if (this.carMaintenanceDao.existsById(updateCarMaintenanceRequest.getMaintenanceId())) {
			CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest,
					CarMaintenance.class);
			this.carMaintenanceDao.save(carMaintenance);
			return new SuccessResult("Güncellendi.");
		} else {
			throw new BusinessException("Bakım bulunamadı.");
		}
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException {

		if (this.carMaintenanceDao.existsById(deleteCarMaintenanceRequest.getMaintenanceId())) {
			CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(deleteCarMaintenanceRequest,
					CarMaintenance.class);
			this.carMaintenanceDao.delete(carMaintenance);
			return new SuccessResult("Bakım silindi.");
		} else {
			throw new BusinessException("Bakım bulunamadı.");
		}
	}

	@Override
	public DataResult<List<CarMaintenanceListDto>> getOnMaintenanceCars() {

		List<CarMaintenance> result = this.carMaintenanceDao.findCarMaintenanceByReturnDateIsNull();
		List<CarMaintenanceListDto> response = result.stream().map(
				carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarMaintenanceListDto>>(response, "Veriler listelendi.");
	}

//	@Override
//    public boolean checkIfCarIsInMaintenance(int id, LocalDate localDate){
//        List<CarMaintenance> result=this.carMaintenanceDao.getAllByCarId(id);
//        int flag=0;
//        for (CarMaintenance carMaintenance : result) {
//            if((carMaintenance.getReturnDate()==null)||
//                    localDate.now().isBefore(carMaintenance.getReturnDate()) ||
//                        localDate.now().isEqual(carMaintenance.getReturnDate())) {
//                flag++;
//            }
//        }
//        if(flag!=0)
//            return true;
//        else
//            return false;
//    }

	@Override
	public boolean checkIfCarIsInMaintenance(int id) {
		int flag = 0;
		List<CarMaintenance> result = this.carMaintenanceDao.getAllByCarId(id);
		List<CarMaintenanceListDto> response = result.stream().map(
				carMaintenance -> this.modelMapperService.forDto().map(carMaintenance, CarMaintenanceListDto.class))
				.collect(Collectors.toList());
		for (CarMaintenanceListDto carMaintenance : response) {
			if ((carMaintenance.getReturnDate() == null) || LocalDate.now().isBefore(carMaintenance.getReturnDate())
					|| LocalDate.now().isEqual(carMaintenance.getReturnDate())) {
				flag++;
			}
		}
		if (flag != 0)
			return true;
		else
			return false;
	}

}
