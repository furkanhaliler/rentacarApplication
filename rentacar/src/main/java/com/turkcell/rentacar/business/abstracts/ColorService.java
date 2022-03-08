package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.ColorListDto;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.requests.*;
import com.turkcell.rentacar.business.requests.create.CreateColorRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.update.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


import java.util.List;

public interface ColorService {
    DataResult<List<ColorListDto>> getAll();
    Result add(CreateColorRequest createColorRequest) throws BusinessException;
    DataResult<GetColorDto> getById(Integer id)  throws BusinessException;
    Result update(UpdateColorRequest updateColorRequest)  throws BusinessException;
    Result delete(DeleteColorRequest deleteColorRequest)  throws BusinessException;
    
}
