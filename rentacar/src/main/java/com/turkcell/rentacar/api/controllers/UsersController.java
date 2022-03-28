package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.UserService;
import com.turkcell.rentacar.business.dtos.gets.GetUserDto;
import com.turkcell.rentacar.business.dtos.lists.UserListDto;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;

@RestController
@RequestMapping("/api/users")
public class UsersController {

	private UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {

		this.userService = userService;
	}

	@GetMapping("/getAll")
	DataResult<List<UserListDto>> getAll(){
		
		return this.userService.getAll();
	}

	@GetMapping("/getByUserId/{userId}")
	DataResult<GetUserDto> getByUserId(@RequestParam("userId") Integer id) throws BusinessException{
		
		return this.userService.getByUserId(id);
	}
	
	
	
}
