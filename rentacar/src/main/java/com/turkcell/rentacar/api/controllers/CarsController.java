package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.GetCarDto;
import com.turkcell.rentacar.business.dtos.CarListDto;
import com.turkcell.rentacar.business.requests.create.CreateCarRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarRequest;
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
    public DataResult<List<CarListDto>> getAll() throws BusinessException {
        return this.carService.getAll();
    }

    @GetMapping("/getByCarId/{carId}")
    public DataResult<GetCarDto> getByCarId(@PathVariable("carId") Integer carId) throws BusinessException{
        return this.carService.getByCarId(carId);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) throws BusinessException{
    	return this.carService.update(updateCarRequest);
        
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteCarRequest deleteCarRequest) throws BusinessException{
        return this.carService.delete(deleteCarRequest);
    }
    
    @GetMapping("/getAllPaged/{pageNumber}/{pageSize}")
    public DataResult<List<CarListDto>> getAllPaged(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize){
    	
    	return this.carService.getAllPaged(pageNumber, pageSize);
    	
    }
    
    @GetMapping("/getAllSorted/{orderOfSort}")
    public DataResult<List<CarListDto>> getAllSorted (@RequestParam("orderOfSort") String orderOfSort)throws BusinessException{
    	
    	return this.carService.getAllSorted(orderOfSort);
    }
    
    @GetMapping("/findByDailyPriceLessThan/{requestedPrice}")
    public DataResult<List<CarListDto>> findByDailyPriceLessThan(@PathVariable("requestedPrice") double requestedPrice){
    	
    	return this.carService.findByDailyPriceLessThan(requestedPrice);
    	
    }
    
    @GetMapping("/findByDailyPriceGreaterThan/{requestedPrice}")
    public DataResult<List<CarListDto>> findByDailyPriceGreaterThan(@PathVariable("requestedPrice") double requestedPrice){
    	
    	return this.carService.findByDailyPriceGreaterThan(requestedPrice);
    }
    
    @GetMapping("/findByDailyPriceBetween/{minValue}/{maxValue}")
    public DataResult<List<CarListDto>> findByDailyPriceBetween 
    (@PathVariable("minValue") double minValue, @PathVariable("maxValue") double maxValue)throws BusinessException{
    	
    	return this.carService.findByDailyPriceBetween(minValue, maxValue);
    }
    
    @GetMapping("/getByBrandId/{brandId}")
    public DataResult<List<GetCarDto>> getByBrandId(@PathVariable("brandId") int brandId)throws BusinessException{
    	
    	return this.carService.getByBrandId(brandId);
    }
    
    @GetMapping("/getByColorId/{colorId}")
    public DataResult<List<GetCarDto>> getByColorId(@PathVariable("colorId") int colorId)throws BusinessException{
    	
    	return this.carService.getByColorId(colorId);
    }
    
    @GetMapping("/getByBrandIdAndColorId/{brandId}/{colorId}")
    public DataResult<List<GetCarDto>> getByBrandIdAndColorId
    (@PathVariable("brandId") int brandId, @PathVariable("colorId") int colorId)throws BusinessException{
    	
    	return this.carService.getByBrandIdAndColorId(brandId, colorId);
    }
}










