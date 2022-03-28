package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.gets.GetColorDto;
import com.turkcell.rentacar.business.dtos.lists.ColorListDto;
import com.turkcell.rentacar.business.requests.color.CreateColorRequest;
import com.turkcell.rentacar.business.requests.color.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.color.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.color.ColorAlreadyExistsException;
import com.turkcell.rentacar.core.exceptions.color.ColorNotfoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentacar.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorManager implements ColorService {

	private ColorDao colorDao;
	private ModelMapperService modelMapperService;

	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {

		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<ColorListDto>> getAll(){

		List<Color> result = this.colorDao.findAll();

		List<ColorListDto> response = result.stream().map(color -> this.modelMapperService
				.forDto().map(color, ColorListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<ColorListDto>>(response, BusinessMessages.COLORS_LISTED);
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) throws BusinessException {

		checkIfColorNameExists(createColorRequest.getColorName());

		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		
		this.colorDao.save(color);
		
		return new SuccessResult(BusinessMessages.COLOR_ADDED);
	}

	@Override
	public DataResult<GetColorDto> getById(Integer id) throws BusinessException {

		checkIfColorIdExists(id);

		Color foundColor = colorDao.getById(id);
		
		GetColorDto response = this.modelMapperService.forDto().map(foundColor, GetColorDto.class);
		
		return new SuccessDataResult<GetColorDto>(response, BusinessMessages.COLOR_FOUND_BY_ID);
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws BusinessException {

		checkIfColorIdExists(updateColorRequest.getColorId());
		checkIfColorNameExists(updateColorRequest.getColorName());

		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		
		this.colorDao.save(color);
		
		return new SuccessResult(BusinessMessages.COLOR_UPDATED);
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException {

		checkIfColorIdExists(deleteColorRequest.getColorId());

		this.colorDao.deleteById(deleteColorRequest.getColorId());
		
		return new SuccessResult(BusinessMessages.COLOR_DELETED);
	}

	@Override
	public void checkIfColorNameExists(String colorName) throws BusinessException {

		if (this.colorDao.existsColorByColorName(colorName)) {
			throw new ColorAlreadyExistsException(BusinessMessages.COLOR_NAME_EXISTS);
		}
	}

	@Override
	public void checkIfColorIdExists(Integer id) throws BusinessException {

		if (!this.colorDao.existsById(id)) {

			throw new ColorNotfoundException(BusinessMessages.COLOR_NOT_FOUND);
		}
	}
}
