package com.turkcell.rentacar.business.dtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPaymentDto {

	private int paymentId;
	private int rentRentId;
	private int invoiceInvoiceId;
	private int customerUserId;
}
