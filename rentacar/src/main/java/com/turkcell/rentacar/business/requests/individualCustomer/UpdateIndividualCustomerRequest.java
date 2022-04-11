package com.turkcell.rentacar.business.requests.individualCustomer;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {


	@NotNull
	@Min(value = 1, message = ValidationMessages.INDIVIDUAL_CUSTOMER_ID_RULE)
	private int userId;
	
	@NotNull
	@Email(message = ValidationMessages.INDIVIDUAL_CUSTOMER_EMAIL_RULE)
	private String email;
	
	@NotNull
	@Size(min = 8, max = 20, message = ValidationMessages.INDIVIDUAL_CUSTOMER_PASSWORD_RULE)
	private String password;
	
	@NotNull
	@Size(min = 2, max = 40, message = ValidationMessages.INDIVIDUAL_CUSTOMER_FIRST_NAME_RULE)
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 40, message = ValidationMessages.INDIVIDUAL_CUSTOMER_LAST_NAME_RULE)
	private String lastName;
	
	@NotNull
	@Pattern(regexp = "[0-9\\d]{11}" , message = ValidationMessages.INDIVIDUAL_CUSTOMER_NATIONAL_IDENTITY_RULE)
	private String nationalIdentity;
	
	
	
}
