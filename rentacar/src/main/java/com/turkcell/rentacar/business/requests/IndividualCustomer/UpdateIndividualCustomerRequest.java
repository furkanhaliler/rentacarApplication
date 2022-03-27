package com.turkcell.rentacar.business.requests.IndividualCustomer;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.turkcell.rentacar.business.constants.messages.BusinessMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {


	@NotNull
	@Min(1)
	private int userId;
	
	@NotNull
	@Email(message = BusinessMessages.WRONG_EMAIL_FORMAT)
	private String email;
	
	@NotNull
	@Size(min = 8, max = 20)
	private String password;
	
	@NotNull
	@Size(min = 2, max = 40)
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 40)
	private String lastName;
	
	@NotNull
	@Size(min = 11, max = 11)
	private String nationalIdentity;
	
	
	
}
