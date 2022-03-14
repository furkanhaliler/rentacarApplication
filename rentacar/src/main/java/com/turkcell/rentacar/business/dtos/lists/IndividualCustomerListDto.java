package com.turkcell.rentacar.business.dtos.lists;

import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetRentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IndividualCustomerListDto {

	private int userId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String nationalIdentity;
	private List<GetRentDto> rents;
}
