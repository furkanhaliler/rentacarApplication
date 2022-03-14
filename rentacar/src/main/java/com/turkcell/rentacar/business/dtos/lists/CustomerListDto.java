package com.turkcell.rentacar.business.dtos.lists;

import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetRentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerListDto {

	private int userId;
	private String email;
	private String password;
	private List<GetRentDto> rents;
}
