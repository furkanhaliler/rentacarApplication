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
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.GetRentDto;
import com.turkcell.rentacar.business.dtos.RentListDto;
import com.turkcell.rentacar.business.requests.create.CreateRentRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.update.UpdateRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rents")
public class RentController {

	private RentService rentService;
	
	
	@Autowired
	public RentController(RentService rentService) {
		super();
		this.rentService = rentService;
	}
	
	@PostMapping("/add")
	Result add(@RequestBody  CreateRentRequest createRentRequest) throws BusinessException{
		return this.rentService.add(createRentRequest);
	}
	
	@PutMapping("/update")
	Result update(@RequestBody UpdateRentRequest updateRentRequest) {
		return this.rentService.update(updateRentRequest);
	}
	
	@DeleteMapping("/delete")
	Result delete(@RequestBody DeleteRentRequest deleteRentRequest) {
		return this.rentService.delete(deleteRentRequest);
	}
	@GetMapping("/getall")
	DataResult<List<RentListDto>> getAll() throws BusinessException{
		
		return this.rentService.getAll();
	}
	
	@GetMapping("/getById")
	DataResult<List<RentListDto>> getById(int id) throws BusinessException{
		
		return this.rentService.getByCarId(id);
	}
	
}
