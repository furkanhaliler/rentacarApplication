package com.turkcell.rentacar.business.requests.creditCard;

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
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {

	@NotNull
	@Min(value = 1, message = ValidationMessages.CREDIT_CARD_CUSTOMER_ID_RULE)
	private int customerUserId;
	
	@NotNull
	@CreditCardNumber(message = ValidationMessages.CREDIT_CARD_NUMBER_RULE)
	private String creditCardNo;
	
	@NotNull
	@Size(min = 5, max = 50, message = ValidationMessages.CREDIT_CARD_HOLDER_NAME_RULE)
	private String creditCardHolder;
	
	@NotNull
	@Min(value = 1, message = ValidationMessages.CREDIT_CARD_MONTH_RULE)
	@Max(value = 12, message = ValidationMessages.CREDIT_CARD_MONTH_RULE)
	private int expirationMonth;
	
	@NotNull
	@Min(value = 2022, message = ValidationMessages.CREDIT_CARD_YEAR_RULE)
	@Max(value = 2100, message = ValidationMessages.CREDIT_CARD_YEAR_RULE)
	private int expirationYear;
	
	@NotNull
	@Size(min = 3, max = 3)
	@Pattern(regexp = "[0-9\\d]{3}", message = ValidationMessages.CREDIT_CARD_CVV_RULE)
	private String cvv;
	
	
}
