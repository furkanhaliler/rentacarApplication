package com.turkcell.rentacar.business.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDto {


	private int userId;
	private String email;
	private String password;
}
