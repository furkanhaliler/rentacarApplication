package com.turkcell.rentacar.business.requests.pos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePosRequest {

	@NotNull
	@CreditCardNumber(message = ValidationMessages.POS_CREDIT_CARD_NUMBER_RULE)
	private String creditCardNo;
	
	@NotNull
	@Size(min = 5, max = 50, message = ValidationMessages.POS_CREDIT_CARD_HOLDER_NAME_RULE)
	private String creditCardHolder;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.POS_CREDIT_CARD_MONTH_RULE)
	@Max(value = 12, message = ValidationMessages.POS_CREDIT_CARD_MONTH_RULE)
	private int expirationMonth;
	
	@NotNull
	@Min(value = 2022, message = ValidationMessages.POS_CREDIT_CARD_YEAR_RULE)
	@Max(value = 2100, message = ValidationMessages.POS_CREDIT_CARD_YEAR_RULE)
	private int expirationYear;
	
	@NotNull
	@Pattern(regexp = "[0-9\\d]{3}", message = ValidationMessages.POS_CREDIT_CARD_CVV_RULE)
	private String cvv;
	




}
