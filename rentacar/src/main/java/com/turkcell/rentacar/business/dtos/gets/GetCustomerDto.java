package com.turkcell.rentacar.business.dtos.gets;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerDto {

	private int userId;
	private String email;
	private String password;
	private List<GetRentDto> rents;
}
