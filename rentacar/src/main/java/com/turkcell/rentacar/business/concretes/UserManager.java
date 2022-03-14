package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.UserService;
import com.turkcell.rentacar.business.dtos.gets.GetUserDto;
import com.turkcell.rentacar.business.dtos.lists.UserListDto;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.dataAccess.abstracts.UserDao;
import com.turkcell.rentacar.entities.concretes.User;

@Service
public class UserManager implements UserService {
	
	private UserDao<User> userDao;
	private ModelMapperService modelMapperService;


	@Autowired
	public UserManager(UserDao<User> userDao, ModelMapperService modelMapperService) {
		
		this.userDao = userDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public DataResult<List<UserListDto>> getAll() throws BusinessException {
		
		List<User> result = this.userDao.findAll();
		
		List<UserListDto> response = result.stream().map(user -> this.modelMapperService
				.forDto().map(user, UserListDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<UserListDto>>(response, "Veriler başarıyla sıralandı.");
		
	}

	@Override
	public DataResult<GetUserDto> getByUserId(Integer id) throws BusinessException {
		
		checkIfUserIdExists(id);
		
		User user = this.userDao.getById(id);
		
		GetUserDto response = this.modelMapperService.forDto().map(user, GetUserDto.class);
		
		return new SuccessDataResult<GetUserDto>(response, "Veri başarıyla getirildi.");
	}

	@Override
	public void checkIfUserIdExists(Integer id) throws BusinessException {
		
		if(!this.userDao.existsById(id)) {
			
			throw new BusinessException("Bu ID'de kayıtlı kullanıcı bulunamadı.");
		}

	}

}
