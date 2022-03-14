package com.turkcell.rentacar.business.dtos.lists;

import java.util.List;

import com.turkcell.rentacar.business.dtos.gets.GetRentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CorporateCustomerListDto {

	private int userId;
	private String email;
	private String password;
	private String companyName;
	private String taxNumber;
	private List<GetRentDto> rents;
}
