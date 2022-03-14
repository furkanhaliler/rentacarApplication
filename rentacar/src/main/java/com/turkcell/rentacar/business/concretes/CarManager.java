package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.gets.GetCarDto;
import com.turkcell.rentacar.business.dtos.lists.CarListDto;
import com.turkcell.rentacar.business.requests.create.CreateCarRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.CarNotFoundException;
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


	@Autowired
	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {

		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		
		this.carDao.save(car);
		
		return new SuccessResult("Araba eklendi.");
	}

	@Override
	public DataResult<GetCarDto> getByCarId(int id) throws BusinessException {

		checkIfCarIdExists(id);

		Car car = carDao.getById(id);
		
		GetCarDto response = this.modelMapperService.forDto().map(car, GetCarDto.class);

		return new SuccessDataResult<GetCarDto>(response, "Id'ye göre listelendi");
	}

	@Override
	public DataResult<List<CarListDto>> getAll() throws BusinessException {

		List<Car> result = this.carDao.findAll();

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<CarListDto>>(response, "Veriler listelendi.");
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) throws BusinessException {

		checkIfCarIdExists(updateCarRequest.getId());

		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		
		this.carDao.save(car);

		return new SuccessResult("Araba güncellendi.");
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException {

		checkIfCarIdExists(deleteCarRequest.getId());

		this.carDao.deleteById(deleteCarRequest.getId());
		
		return new SuccessResult("Araba silindi.");
	}

	@Override
	public DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		List<Car> result = this.carDao.findAll(pageable).getContent();
		
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
		
		return new SuccessDataResult<List<CarListDto>>(response, "Veriler başarıyla sıralandı.");
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
	public DataResult<List<CarListDto>> findByDailyPriceBetween(double minValue, double maxValue) throws BusinessException {

		if (minValue > maxValue) {
			double a = minValue;
			minValue = maxValue;
			maxValue = a;
		}
		
		List<Car> result = this.carDao.findByDailyPriceBetween(minValue, maxValue);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<CarListDto>>(response, "Başarıyla listelendi.");
	}

	@Override
	public void checkIfCarIdExists(Integer id) throws BusinessException {

		if (!this.carDao.existsById(id)) {

			throw new CarNotFoundException("Bu ID'de kayıtlı araba bulunamadı.");
		}
	}

}
