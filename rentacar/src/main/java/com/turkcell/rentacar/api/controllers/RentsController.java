package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.RentService;
import com.turkcell.rentacar.business.dtos.rent.GetRentDto;
import com.turkcell.rentacar.business.dtos.rent.RentListDto;
import com.turkcell.rentacar.business.requests.rent.DeleteRentRequest;
import com.turkcell.rentacar.business.requests.rent.EndRentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rents")
public class RentsController {

	private RentService rentService;

	@Autowired
	public RentsController(RentService rentService) {

		this.rentService = rentService;
	}

	@GetMapping("/getall")
	DataResult<List<RentListDto>> getAll(){

		return this.rentService.getAll();
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteRentRequest deleteRentRequest) throws BusinessException {

		return this.rentService.delete(deleteRentRequest);
	}

	@GetMapping("/getByCarId")
	DataResult<List<RentListDto>> getByCarId(int id) throws BusinessException {

		return this.rentService.getByCarId(id);
	}
	
	@GetMapping("/getByRentId")
	DataResult<GetRentDto> getByRentId(int id) throws BusinessException{
		
		return this.rentService.getByRentId(id);
	}
	
	@PostMapping("/endRent")
	Result endRent(@RequestBody @Valid EndRentRequest endRentRequest) throws BusinessException{
		
		return this.rentService.endRent(endRentRequest);
	}
}
