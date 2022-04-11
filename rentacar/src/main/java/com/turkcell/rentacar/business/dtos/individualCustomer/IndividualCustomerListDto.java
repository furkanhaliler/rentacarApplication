package com.turkcell.rentacar.business.dtos.individualCustomer;

import java.util.List;

import com.turkcell.rentacar.business.dtos.creditCard.CreditCardDTO;
import com.turkcell.rentacar.business.dtos.invoice.InvoiceDTO;
import com.turkcell.rentacar.business.dtos.rent.RentDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IndividualCustomerListDto {

	private int userId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String nationalIdentity;
	private List<RentDTO> rents;
	private List<InvoiceDTO> invoices;
	private List<CreditCardDTO> creditCards;
}
