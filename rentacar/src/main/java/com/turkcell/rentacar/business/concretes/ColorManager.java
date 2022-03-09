package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.ColorListDto;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.requests.create.CreateColorRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.update.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.exceptions.ColorAlreadyExistException;
import com.turkcell.rentacar.core.exceptions.ColorNotfoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
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
		
		if(result.isEmpty()) {
			throw new ColorNotfoundException("Listede henüz hiç renk yok.");
		}

		List<ColorListDto> response = result.stream()
				.map(color -> this.modelMapperService.forDto().map(color, ColorListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ColorListDto>>(response, "Veriler listelendi");
	}

	@Override
	public Result add(CreateColorRequest createColorRequest) throws BusinessException {
		if (this.colorDao.existsColorByColorName(createColorRequest.getColorName())) {
			throw new ColorAlreadyExistException("Aynı isimde renk kayıtlı");
		} else {
			Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
			this.colorDao.save(color);
			return new SuccessResult("Renk eklendi.");
		}
	}

	@Override
	public DataResult<GetColorDto> getById(Integer id) throws BusinessException {
		if (this.colorDao.existsById(id)) {
			Color foundColor = colorDao.getById(id);
			GetColorDto response = this.modelMapperService.forDto().map(foundColor, GetColorDto.class);
			return new SuccessDataResult<GetColorDto>(response, "Veri getirildi.");
		} else {
			throw new ColorNotfoundException("Bu id'de renk bulunamadı");
		}
	}

	@Override
    public Result update(UpdateColorRequest updateColorRequest)  throws BusinessException{
        if (this.colorDao.findById(updateColorRequest.getColorId()).isPresent() &&
        		!this.colorDao.existsColorByColorName(updateColorRequest.getColorName())) {
        	
        	Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
            this.colorDao.save(color);
            return new SuccessResult("Veri güncellendi");
        	
        }else if(this.colorDao.existsColorByColorName(updateColorRequest.getColorName())){
        	throw new ColorAlreadyExistException("Bu isimde başka bir renk zaten var.");
        }else {
        	throw new ColorNotfoundException("Bu Id'de kayıtlı renk bulunamadı.");
        }
    }

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException {
		if (this.colorDao.existsById(deleteColorRequest.getColorId())) {
			this.colorDao.deleteById(deleteColorRequest.getColorId());
			return new SuccessResult("Veri silindi");
		} else {
			throw new ColorNotfoundException("Bu Id'de kayıtlı renk bulunamadı.");
		}
	}
}
