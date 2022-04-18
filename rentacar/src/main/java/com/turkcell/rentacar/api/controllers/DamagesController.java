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

import com.turkcell.rentacar.business.abstracts.DamageService;
import com.turkcell.rentacar.business.dtos.damage.DamageListDto;
import com.turkcell.rentacar.business.dtos.damage.GetDamageDto;
import com.turkcell.rentacar.business.requests.damage.CreateDamageRequest;
import com.turkcell.rentacar.business.requests.damage.DeleteDamageRequest;
import com.turkcell.rentacar.business.requests.damage.UpdateDamageRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/damages")
public class DamagesController {
	
	private DamageService damageService;

	@Autowired
	public DamagesController(DamageService damageService) {
		
		this.damageService = damageService;
	}
	
	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateDamageRequest createDamageRequest) throws BusinessException{
		
		return this.damageService.add(createDamageRequest);
	}

	@GetMapping("/getAll")
	DataResult<List<DamageListDto>> getAll(){
		
		return this.damageService.getAll();
	}

	@GetMapping("/getByDamageId/{damageId}")
	DataResult<GetDamageDto> getById(@RequestParam("damageId") Integer id) throws BusinessException{
		
		return this.damageService.getById(id);
	}

	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateDamageRequest updateDamageRequest) throws BusinessException{
		
		return this.damageService.update(updateDamageRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteDamageRequest deleteDamageRequest) throws BusinessException{
		
		return this.damageService.delete(deleteDamageRequest);
	}
	
	@GetMapping("/getByCarId/{carId}")
	DataResult<List<DamageListDto>> getByCarId(@RequestParam("carId") Integer carId) throws BusinessException{
		
		return this.damageService.getByCarId(carId);
	}	
}
