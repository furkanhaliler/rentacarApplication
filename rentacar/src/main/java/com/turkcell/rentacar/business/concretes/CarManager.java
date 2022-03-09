package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.GetCarDto;
import com.turkcell.rentacar.business.dtos.CarListDto;
import com.turkcell.rentacar.business.requests.create.CreateCarRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.CarNotFoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CarDao;
import com.turkcell.rentacar.entities.concretes.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private ModelMapperService modelMapperService;
	private RentService rentService;
	private CarMaintenanceService carMaintenanceService;

	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService, @Lazy RentService rentService,
			@Lazy CarMaintenanceService carMaintenanceService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
		this.rentService = rentService;
		this.carMaintenanceService = carMaintenanceService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult("Araba eklendi.");
	}

	@Override
	public DataResult<GetCarDto> getByCarId(int id) throws BusinessException {
		if (this.carDao.existsCarById(id)) {
			Car car = carDao.getById(id);
			GetCarDto response = this.modelMapperService.forDto().map(car, GetCarDto.class);
			updateCarMaintenanceStatus(car.getId(), this.carMaintenanceService.checkIfCarIsInMaintenance(car.getId()));
			updateRentStatus(car.getId(), this.rentService.checkIfCarIsRented(car.getId()));
			return new SuccessDataResult<GetCarDto>(response, "Id'ye göre listelendi");
		} else {
			throw new CarNotFoundException("Bu Id'de araç bulunamadı");
		}
	}

	@Override
	public DataResult<List<CarListDto>> getAll() throws BusinessException {
		List<Car> result = this.carDao.findAll();
		if (result.isEmpty()) {
			throw new BusinessException("Listede eleman yok.");
		}
		for (Car car : result) {
			updateCarMaintenanceStatus(car.getId(), this.carMaintenanceService.checkIfCarIsInMaintenance(car.getId()));
			updateRentStatus(car.getId(), this.rentService.checkIfCarIsRented(car.getId()));
		}
		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response, "Veriler listelendi.");
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) throws BusinessException {
		if (this.carDao.existsCarById(updateCarRequest.getId())) {
			Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
			this.carDao.save(car);
			return new SuccessResult("Araba güncellendi.");
		} else {
			throw new CarNotFoundException("Araba bulunamadı");
		}
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException {
		if (this.carDao.existsCarById(deleteCarRequest.getId())) {
			this.carDao.deleteById(deleteCarRequest.getId());
			return new SuccessResult("Araba silindi.");
		} else {
			throw new CarNotFoundException("Araba bulunamadı");
		}

	}

	@Override
	public DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize) {

		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		List<Car> result = this.carDao.findAll(pageable).getContent();

		for (Car car : result) {
			updateCarMaintenanceStatus(car.getId(), this.carMaintenanceService.checkIfCarIsInMaintenance(car.getId()));
			updateRentStatus(car.getId(), this.rentService.checkIfCarIsRented(car.getId()));
		}
		
		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response, "Başarıyla sayfalandı");
	}

	@Override
	public DataResult<List<CarListDto>> getAllSorted(String param) throws BusinessException {

		Sort sort;

		if (param.toUpperCase().equals("ASC")) {
			sort = Sort.by(Sort.Direction.ASC, "dailyPrice");
		} else if (param.toUpperCase().equals("DESC")) {
			sort = Sort.by(Sort.Direction.DESC, "dailyPrice");
		} else {
			throw new BusinessException("Sıralama başarısız, lütfen geçerli bir parametre yazın. (asc, desc)");

		}

		List<Car> result = this.carDao.findAll(sort);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response, "Sıralama başarılı");
	}

	@Override
	public DataResult<List<CarListDto>> findByDailyPriceLessThan(double requestedPrice) {

		List<Car> result = this.carDao.findByDailyPriceLessThan(requestedPrice);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response, "Başarıyla listelendi.");
	}

	@Override
	public DataResult<List<CarListDto>> findByDailyPriceGreaterThan(double requestedPrice) {

		List<Car> result = this.carDao.findByDailyPriceGreaterThan(requestedPrice);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response, "Başarıyla listelendi.");
	}

	@Override
	public DataResult<List<CarListDto>> findByDailyPriceBetween(double minValue, double maxValue)
			throws BusinessException {

		if (maxValue >= minValue) {
			List<Car> result = this.carDao.findByDailyPriceBetween(minValue, maxValue);

			List<CarListDto> response = result.stream()
					.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
					.collect(Collectors.toList());
			return new SuccessDataResult<List<CarListDto>>(response, "Başarıyla listelendi.");
		} else {
			throw new BusinessException("Belirtilen fiyat aralığı yanlış.");

		}

	}

	@Override
	public DataResult<List<GetCarDto>> getByBrandId(int brandId) throws BusinessException {

		if (this.carDao.existsByBrandId(brandId)) {

			List<Car> result = this.carDao.findByBrandId(brandId);

			List<GetCarDto> response = result.stream()
					.map(car -> this.modelMapperService.forDto().map(car, GetCarDto.class))
					.collect(Collectors.toList());
			return new SuccessDataResult<List<GetCarDto>>(response, "Veriler listelendi");
		} else {
			throw new BusinessException("Belirtilen markada araç bulunamadı.");
		}

	}

	@Override
	public DataResult<List<GetCarDto>> getByColorId(int colorId) throws BusinessException {

		if (this.carDao.existsByColorId(colorId)) {

			List<Car> result = this.carDao.findByColorId(colorId);

			List<GetCarDto> response = result.stream()
					.map(car -> this.modelMapperService.forDto().map(car, GetCarDto.class))
					.collect(Collectors.toList());
			return new SuccessDataResult<List<GetCarDto>>(response, "Veriler listelendi.");
		} else {
			throw new BusinessException("Belirtilen renkte araç bulunamadı.");		
		}

	}

	@Override
	public DataResult<List<GetCarDto>> getByBrandIdAndColorId(int brandId, int colorId) throws BusinessException {

		if (this.carDao.existsByBrandId(brandId) && this.carDao.existsByColorId(colorId)) {

			List<Car> result = this.carDao.findByBrandIdAndColorId(brandId, colorId);

			List<GetCarDto> response = result.stream()
					.map(car -> this.modelMapperService.forDto().map(car, GetCarDto.class))
					.collect(Collectors.toList());
			return new SuccessDataResult<List<GetCarDto>>(response, "Veriler listelendi");
		} else {
			throw new BusinessException("Bu renk ve markaya sahip bir araç bulunamadı.");
		}

	}

	@Override
	public void updateRentStatus(int id, boolean status) {

		Car car = this.carDao.getById(id);
		car.setRentStatus(status);
		carDao.save(car);

	}

	@Override
	public void updateCarMaintenanceStatus(int id, boolean status) {

		Car car = this.carDao.getById(id);
		car.setCarMaintenanceStatus(status);
		carDao.save(car);

	}

}
