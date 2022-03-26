package com.turkcell.rentacar.business.requests.Invoice;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInvoiceRequest {

	@NotNull
	@Min(1)
	private int invoiceId;

	@NotNull
	@Size(min = 2, max = 50)
	private String invoiceNumber;

	@NotNull
	@Min(1)
	private int rentRentId;

}
