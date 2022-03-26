package com.turkcell.rentacar.business.dtos.gets;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetIndividualCustomerDto {

	private int userId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String nationalIdentity;
	private List<GetRentDto> rents;
	private List<GetInvoiceDto> invoices;
	private List<GetPaymentDto> payments;
}
