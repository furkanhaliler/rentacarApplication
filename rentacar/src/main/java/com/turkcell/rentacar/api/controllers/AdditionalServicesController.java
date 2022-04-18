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

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.dtos.additionalService.AdditionalServiceListDto;
import com.turkcell.rentacar.business.dtos.additionalService.GetAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.additionalService.CreateAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.additionalService.DeleteAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.additionalService.UpdateAdditionalServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalServices")
public class AdditionalServicesController {
	
	private AdditionalServiceService additionalServiceService;
	
	@Autowired
	public AdditionalServicesController(AdditionalServiceService additionalServiceService) {
		
		this.additionalServiceService = additionalServiceService;
	}

	@GetMapping("/getAll")
	DataResult<List<AdditionalServiceListDto>> getAll(){
		
		return this.additionalServiceService.getAll();
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest) throws BusinessException{
		
		return this.additionalServiceService.add(createAdditionalServiceRequest);
	}

	@GetMapping("/getByAdditionalServiceId/{additionalServiceId}")
	DataResult<GetAdditionalServiceDto> getByAdditionalServiceId(@RequestParam("additionalServiceId") Integer id) throws BusinessException{
		
		return this.additionalServiceService.getByAdditionalServiceId(id);
	}

	@PutMapping("/update")
	Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) throws BusinessException{
		
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
	}

	@DeleteMapping("/delete")
	Result delete(@RequestBody @Valid DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) throws BusinessException{
		
		return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
	}
}
