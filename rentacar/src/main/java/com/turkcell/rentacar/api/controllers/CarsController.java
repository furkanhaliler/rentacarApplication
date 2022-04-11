package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.car.CarListDto;
import com.turkcell.rentacar.business.dtos.car.GetCarDto;
import com.turkcell.rentacar.business.requests.car.CreateCarRequest;
import com.turkcell.rentacar.business.requests.car.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.car.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

	private CarService carService;

	@Autowired
	public CarsController(CarService carService) {

		this.carService = carService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) throws BusinessException{

		return this.carService.add(createCarRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<CarListDto>> getAll(){

		return this.carService.getAll();
	}

	@GetMapping("/getByCarId/{carId}")
	public DataResult<GetCarDto> getByCarId(@PathVariable("carId") Integer carId) throws BusinessException {

		return this.carService.getByCarId(carId);
	}

	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) throws BusinessException {
		
		return this.carService.update(updateCarRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) throws BusinessException {
		
		return this.carService.delete(deleteCarRequest);
	}

	@GetMapping("/getAllPaged/{pageNumber}/{pageSize}")
	public DataResult<List<CarListDto>> getAllPaged(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize)
								throws BusinessException{

		return this.carService.getAllPaged(pageNumber, pageSize);
	}

	@GetMapping("/getAllSorted/{orderOfSort}")
	public DataResult<List<CarListDto>> getAllSorted(@RequestParam("orderOfSort") String orderOfSort) throws BusinessException {

		return this.carService.getAllSorted(orderOfSort);
	}

	@GetMapping("/findByDailyPriceLessThan/{requestedPrice}")
	public DataResult<List<CarListDto>> findByDailyPriceLessThan(@PathVariable("requestedPrice") double requestedPrice) {

		return this.carService.findByDailyPriceLessThan(requestedPrice);
	}

	@GetMapping("/findByDailyPriceGreaterThan/{requestedPrice}")
	public DataResult<List<CarListDto>> findByDailyPriceGreaterThan(@PathVariable("requestedPrice") double requestedPrice) {

		return this.carService.findByDailyPriceGreaterThan(requestedPrice);
	}

	@GetMapping("/findByDailyPriceBetween/{minValue}/{maxValue}")
	public DataResult<List<CarListDto>> findByDailyPriceBetween(@PathVariable("minValue") double minValue,
			@PathVariable("maxValue") double maxValue){

		return this.carService.findByDailyPriceBetween(minValue, maxValue);
	}

}
