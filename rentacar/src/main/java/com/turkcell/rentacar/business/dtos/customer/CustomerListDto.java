package com.turkcell.rentacar.business.dtos.customer;

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

}
