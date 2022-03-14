package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.gets.GetColorDto;
import com.turkcell.rentacar.business.dtos.lists.ColorListDto;
import com.turkcell.rentacar.business.requests.create.CreateColorRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.update.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.ColorAlreadyExistsException;
import com.turkcell.rentacar.core.exceptions.ColorNotfoundException;
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
	public DataResult<List<ColorListDto>> getAll() throws BusinessException {

		List<Color> result = this.colorDao.findAll();

		List<ColorListDto> response = result.stream().map(color -> this.modelMapperService
				.forDto().map(color, ColorListDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<ColorListDto>>(response, "Veriler listelendi");
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) throws BusinessException {

		checkIfColorNameExists(createColorRequest.getColorName());

		Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
		
		this.colorDao.save(color);
		
		return new SuccessResult("Renk eklendi.");
	}

	@Override
	public DataResult<GetColorDto> getById(Integer id) throws BusinessException {

		checkIfColorIdExists(id);

		Color foundColor = colorDao.getById(id);
		
		GetColorDto response = this.modelMapperService.forDto().map(foundColor, GetColorDto.class);
		
		return new SuccessDataResult<GetColorDto>(response, "Veri getirildi.");
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) throws BusinessException {

		checkIfColorIdExists(updateColorRequest.getColorId());
		checkIfColorNameExists(updateColorRequest.getColorName());

		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		
		this.colorDao.save(color);
		
		return new SuccessResult("Veri güncellendi");
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException {

		checkIfColorIdExists(deleteColorRequest.getColorId());

		this.colorDao.deleteById(deleteColorRequest.getColorId());
		
		return new SuccessResult("Veri silindi");
	}

	@Override
	public void checkIfColorNameExists(String colorName) throws BusinessException {

		if (this.colorDao.existsColorByColorName(colorName)) {
			throw new ColorAlreadyExistsException("Bu isimde renk zaten mevcut.");
		}
	}

	@Override
	public void checkIfColorIdExists(Integer id) throws BusinessException {

		if (!this.colorDao.existsById(id)) {

			throw new ColorNotfoundException("Bu ID'de kayıtlı renk bulunamadı.");
		}
	}
}
