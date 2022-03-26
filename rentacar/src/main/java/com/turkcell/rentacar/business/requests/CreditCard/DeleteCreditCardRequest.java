package com.turkcell.rentacar.business.requests.CreditCard;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCreditCardRequest {

	@NotNull
	@Min(0)
	private int creditCardId;
}
