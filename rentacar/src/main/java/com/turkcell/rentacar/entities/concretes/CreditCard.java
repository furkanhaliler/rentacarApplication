package com.turkcell.rentacar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit_cards")
public class CreditCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credit_card_id")
	private int creditCardId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customer;
	
	@Column(name = "credit_card_no")
	private String creditCardNo;
	
	@Column(name = "credit_card_holder")
	private String creditCardHolder;
	
	@Column(name = "expiration_month")
	private int expirationMonth;
	
	@Column(name = "expiration_year")
	private int expirationYear;
	
	@Column(name = "cvv")
	private String cvv;
	
}
