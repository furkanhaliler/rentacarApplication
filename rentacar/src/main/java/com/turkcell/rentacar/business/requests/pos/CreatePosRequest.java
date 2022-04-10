package com.turkcell.rentacar.business.requests.pos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.turkcell.rentacar.business.constants.messages.BusinessMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePosRequest {

	@NotNull
	@CreditCardNumber(message = BusinessMessages.INVALID_CARD_NUMBER)
	private String creditCardNo;
	
	@NotNull
	@Size(min = 5)
	private String creditCardHolder;
	
	@NotNull
	@Min(1)
	@Max(12)
	private int expirationMonth;
	
	@NotNull
	@Min(2022)
	@Max(2050)
	private int expirationYear;
	
	@NotNull
	@Size(min = 3, max = 3)
	@Pattern(regexp = "[0-9\\d]{3}", message = BusinessMessages.INVALID_CVV)
	private String cvv;
	




}
