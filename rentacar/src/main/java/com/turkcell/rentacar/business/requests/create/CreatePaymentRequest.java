package com.turkcell.rentacar.business.requests.create;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentRequest {

	@NotNull
	@CreditCardNumber
	private String cardNo;
	
	@NotNull
	@Size(min = 4)
	private String cardHolder;
	
	@NotNull
	@Size(min = 3, max = 3)
	private String cvv;
	
	@NotNull
	@Min(1)
	@Max(12)
	private int expirationMonth;
	
	@Max(2050)
	private int expirationYear;



}
