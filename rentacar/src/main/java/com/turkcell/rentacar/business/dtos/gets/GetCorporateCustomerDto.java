package com.turkcell.rentacar.business.dtos.gets;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetCorporateCustomerDto {

	private int userId;
	private String email;
	private String password;
	private String companyName;
	private String taxNumber;
	private List<GetRentDto> rents;
}
