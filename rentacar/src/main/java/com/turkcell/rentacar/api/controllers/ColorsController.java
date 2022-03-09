package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.ColorListDto;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.requests.*;
import com.turkcell.rentacar.business.requests.create.CreateColorRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.update.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

    private ColorService colorService;
    
    @Autowired
    public ColorsController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateColorRequest createColorRequest) throws BusinessException{
        
    	return this.colorService.add(createColorRequest);
    }
   
    @GetMapping("/getAll")
    public DataResult<List<ColorListDto>> getAll() throws BusinessException {
        return this.colorService.getAll();
    }

    @GetMapping("/getById/{id}")
    public DataResult<GetColorDto> getColorById(@PathVariable Integer id)  throws BusinessException{
        return this.colorService.getById(id);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) throws BusinessException{
        
    	return this.colorService.update(updateColorRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) throws BusinessException{
       
    	return this.colorService.delete(deleteColorRequest);
    }
}
