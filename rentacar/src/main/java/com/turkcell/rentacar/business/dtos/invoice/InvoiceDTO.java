package com.turkcell.rentacar.business.dtos.invoice;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

	private int invoiceId;
	private String invoiceNumber;
	private LocalDate creationDate;
	private double totalPrice;
}
