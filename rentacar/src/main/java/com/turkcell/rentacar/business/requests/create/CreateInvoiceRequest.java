package com.turkcell.rentacar.business.requests.create;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {

	@NotNull
	@Size(min = 2, max = 20)
	private String invoiceNumber;
	
	@NotNull
	@Min(1)
	private int customerUserId;
	
	@NotNull
	@Min(1)
	private int rentRentId;

}
