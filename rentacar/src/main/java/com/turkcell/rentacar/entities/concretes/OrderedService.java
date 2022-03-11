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
@Table(name = "ordered_services")
public class OrderedService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordered_service_id")
	private int id;
	
	@Column(name = "ordered_service_amount")
	private int orderedServiceAmount;
	
	@ManyToOne
	@JoinColumn(name = "additional_service_id")
	private AdditionalService additionalService;
	
	@ManyToOne
	@JoinColumn(name = "rent_id")
	private Rent rent;
	
	

}
