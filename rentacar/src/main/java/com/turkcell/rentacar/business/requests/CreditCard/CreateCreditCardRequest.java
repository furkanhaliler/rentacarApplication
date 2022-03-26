package com.turkcell.rentacar.business.requests.CreditCard;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {

	@NotNull
	@Size(min = 2)
	private String creditCardDescription;
	
	@NotNull
	@Min(1)
	private int customerUserId;
	
	@NotNull
	@CreditCardNumber
	private String creditCarNo;
	
	@NotNull
	@Size(min = 5)
	private String creditCardHolder;
	
	@NotNull
	@Min(1)
	@Max(12)
	private int expirationMonth;
	
	@NotNull
	@Min(2022)
	private int expirationYear;
	
	@NotNull
	@Size(min = 3, max = 3)
	private String cvv;
	
	
}
