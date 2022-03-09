package com.turkcell.rentacar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.CarMaintenanceListDto;
import com.turkcell.rentacar.business.dtos.GetRentDto;
import com.turkcell.rentacar.business.dtos.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.CarNotFoundException;
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
	public Result add(CreateRentRequest createRentRequest) throws BusinessException {

		Rent rent = this.modelMapperService.forRequest().map(createRentRequest, Rent.class);
		rent.setRentId(0);
		this.carService.updateCarMaintenanceStatus(createRentRequest.getCarId(),
				this.carMaintenanceService.checkIfCarIsInMaintenance(createRentRequest.getCarId()));
		if (carService.getByCarId(createRentRequest.getCarId()).getData().isCarMaintenanceStatus()) {
			throw new BusinessException("Araç bakımda olduğu için kiralanamaz.");
		} else {
			this.rentDao.save(rent);
			this.carService.updateRentStatus(createRentRequest.getCarId(), true);
			return new SuccessResult("Kiralama başarıyla eklendi.");
		}
	}

	@Override
	public Result update(UpdateRentRequest updateRentRequest) {

		Rent rent = this.rentDao.getById(updateRentRequest.getRentId());
		rent = this.modelMapperService.forRequest().map(updateRentRequest, Rent.class);
		this.rentDao.save(rent);
		return new SuccessResult("Kiralama başarıyla güncellendi.");
	}

	@Override
	public Result delete(DeleteRentRequest deleteRentRequest) throws BusinessException {

		Rent rent = this.rentDao.getById(deleteRentRequest.getRentId());
		
		if(!rentDao.existsById(deleteRentRequest.getRentId())) {
			throw new BusinessException("Bu ID'de kiralama bulunamadı.");
		}
		
		this.rentDao.delete(rent);
		return new SuccessResult("Kiralama başarıyla silindi.");
	}

	@Override
	public DataResult<List<RentListDto>> getAll() throws BusinessException {

		List<Rent> rents = this.rentDao.findAll();
		if (rents.isEmpty())
			throw new BusinessException("Listede hiç kiralama yok.");
		List<RentListDto> response = rents.stream()
				.map(rent -> this.modelMapperService.forDto().map(rent, RentListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<RentListDto>>(response, "Veriler başarıyla listelendi");
	}

	@Override
	public DataResult<List<RentListDto>> getByCarId(int id) throws BusinessException {

		List<Rent> rents = this.rentDao.getAllByCarId(id);
		if (rents.isEmpty())
			throw new BusinessException("Bu ID'de kayıtlı kiralama bulunamadı.");
		List<RentListDto> response = rents.stream()
				.map(rent -> this.modelMapperService.forDto().map(rent, RentListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<RentListDto>>(response, "Veriler başarıyla listelendi");
	}

	@Override
	public boolean checkIfCarIsRented(int id) {

		List<Rent> result = this.rentDao.getAllByCarId(id);
		int flag = 0;
		List<RentListDto> response = result.stream()
				.map(rent -> this.modelMapperService.forDto().map(rent, RentListDto.class))
				.collect(Collectors.toList());
		for (RentListDto rent : response) {
			if (rent.getReturnDate() == null || LocalDate.now().isBefore(rent.getReturnDate())
					|| LocalDate.now().isEqual(rent.getReturnDate()))
				flag++;
		}
		if (flag != 0)
			return true;
		else
			return false;
	}

}
