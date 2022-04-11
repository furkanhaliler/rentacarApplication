package com.turkcell.rentacar.business.dtos.corporateCustomer;

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
public class GetCorporateCustomerDto {

	private int userId;
	private String email;
	private String password;
	private String companyName;
	private String taxNumber;
	private List<RentDTO> rents;
	private List<InvoiceDTO> invoices;
	private List<CreditCardDTO> creditCards;
}
