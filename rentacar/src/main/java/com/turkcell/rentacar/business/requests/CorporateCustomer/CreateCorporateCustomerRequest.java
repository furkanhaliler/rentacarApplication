package com.turkcell.rentacar.business.requests.CorporateCustomer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {

	@NotNull
	@Email(message = "Lütfen düzgün bir e-posta adresi girdiğinizden emin olun.")
	private String email;
	
	@NotNull
	@Size(min = 8, max = 20)
	private String password;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String companyName;
	
	@NotNull
	@Size(min = 11,max = 11)
	private String taxNumber;
}
