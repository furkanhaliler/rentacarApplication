package com.turkcell.rentacar.business.requests.corporateCustomer;

import javax.validation.constraints.Email;
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
public class CreateCorporateCustomerRequest {

	@NotNull
	@Email(message = ValidationMessages.CORPORATE_CUSTOMER_EMAIL_RULE)
	private String email;
	
	@NotNull
	@Size(min = 8, max = 20, message = ValidationMessages.CORPORATE_CUSTOMER_PASSWORD_RULE)
	private String password;
	
	@NotNull
	@Size(min = 2, max = 100, message = ValidationMessages.CORPORATE_CUSTOMER_COMPANY_NAME_RULE)
	private String companyName;
	
	@NotNull
	@Pattern(regexp = "[0-9\\d]{11}", message = ValidationMessages.CORPORATE_CUSTOMER_TAX_NUMBER_RULE)
	private String taxNumber;
}
