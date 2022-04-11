package com.turkcell.rentacar.business.requests.Invoice;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.turkcell.rentacar.business.constants.messages.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

	@NotNull
	@Min(value = 1, message = ValidationMessages.INVOICE_ID_RULE)
	private int invoiceId;

	@NotNull
	@Size(min = 2, max = 50, message = ValidationMessages.INVOICE_NUMBER_RULE)
	private String invoiceNumber;

	@NotNull
	@Min(value = 1, message = ValidationMessages.INVOICE_RENT_ID_RULE)
	private int rentRentId;

}
