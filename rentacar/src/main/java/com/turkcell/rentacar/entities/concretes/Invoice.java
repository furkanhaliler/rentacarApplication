package com.turkcell.rentacar.entities.concretes;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoices")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	private int invoiceId;
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	@Column(name = "creation_date")
	private LocalDate creationDate;
	
	@Column(name = "rent_start_date")
	private LocalDate rentStartDate;
	
	@Column(name = "rent_return_date")
	private LocalDate rentReturnDate;
	
	@Column(name = "total_rent_day")
	private int totalRentDay;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customer;
	
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "rent_id")
	private Rent rent;
	
	
	
}
