package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.user.GetUserDto;
import com.turkcell.rentacar.business.dtos.user.UserListDto;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;

public interface UserService {

	DataResult<List<UserListDto>> getAll();

	DataResult<GetUserDto> getByUserId(Integer id) throws BusinessException;
	 
	void checkIfUserIdExists (Integer id) throws BusinessException;
	
}
