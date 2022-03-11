package com.turkcell.rentacar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "rents")
public class Rent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rent_id")
	private int rentId;

	@Column(name = "start_date")
	private LocalDate rentStartDate;

	@Column(name = "return_date")
	private LocalDate rentReturnDate;
	
	@ManyToOne
	@JoinColumn(name = "rent_city_id")
	private City rentCity;
	
	@ManyToOne
	@JoinColumn(name ="return_city_id")
	private City returnCity;
	
	@Column(name = "total_price")
	private double totalPrice;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@OneToMany(mappedBy = "rent")
	private List<OrderedService> orderedService;
}