package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.dtos.*;
import com.turkcell.rentacar.business.requests.*;
import com.turkcell.rentacar.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/carMaintenances")
public class CarMaintenancesController {
	
	private CarMaintenanceService carMaintenanceService;
	
	@Autowired
	public CarMaintenancesController(CarMaintenanceService carMaintenanceService) {
		
		this.carMaintenanceService = carMaintenanceService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException{
		
		return this.carMaintenanceService.add(createCarMaintenanceRequest);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<CarMaintenanceListDto>> getAll() throws BusinessException{
		
		return this.carMaintenanceService.getAll();
	}
	
	@GetMapping("/getByCarId")
	public DataResult<List<CarMaintenanceListDto>> getByCarId(@RequestParam Integer id)throws BusinessException{
		
		return this.carMaintenanceService.getByCarId(id);
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) throws BusinessException{
		
		return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}
	
	@PutMapping("/put")
	public Result update(@RequestBody @Valid UpdateCarMaintenanceRequest updateCarMaintenanceRequest)throws BusinessException {
		
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}
	
	@GetMapping("/getAllOnMaintenanceCars")
	public DataResult<List<CarMaintenanceListDto>> getAllOnMaintenanceCars(){
		
		return this.carMaintenanceService.getOnMaintenanceCars();
	}
	
	
}
