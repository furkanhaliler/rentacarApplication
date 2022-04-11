package com.turkcell.rentacar.business.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentListDto {

	private int paymentId;
	private int rentRentId;
	private int invoiceInvoiceId;
	private int customerUserId;
}
