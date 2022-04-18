package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CityService;
import com.turkcell.rentacar.business.dtos.city.CityListDto;
import com.turkcell.rentacar.business.dtos.city.GetCityDto;
import com.turkcell.rentacar.business.requests.city.CreateCityRequest;
import com.turkcell.rentacar.business.requests.city.DeleteCityRequest;
import com.turkcell.rentacar.business.requests.city.UpdateCityRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
	
	private CityService cityService;
	
	@Autowired
	public CitiesController(CityService cityService) {
		
		this.cityService = cityService;
	}

	@GetMapping("/getAll")
	DataResult<List<CityListDto>> getAll(){
		
		return this.cityService.getAll();
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateCityRequest createCityRequest) throws BusinessException{
		
		return this.cityService.add(createCityRequest);
	}

	@GetMapping("/getById/{cityId}")
	DataResult<GetCityDto> getById(@RequestParam("cityId") Integer id) throws BusinessException{
		
		return this.cityService.getById(id);
	}

	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateCityRequest updateCityRequest) throws BusinessException{
		
		return this.cityService.update(updateCityRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteCityRequest deleteCityRequest) throws BusinessException{
		
		return this.cityService.delete(deleteCityRequest);
	}
}
