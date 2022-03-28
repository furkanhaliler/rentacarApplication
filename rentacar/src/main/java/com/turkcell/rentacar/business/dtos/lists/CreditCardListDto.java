package com.turkcell.rentacar.business.dtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardListDto {

	private int creditCardId;

	private int customerUserId;

	private String creditCarNo;

	private String creditCardHolder;

	private int expirationMonth;

	private int expirationYear;

	private String cvv;
}
