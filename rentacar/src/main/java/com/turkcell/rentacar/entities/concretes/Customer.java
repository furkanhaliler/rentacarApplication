package com.turkcell.rentacar.entities.concretes;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customers")
public class Customer extends User{

	@OneToMany(mappedBy = "customer")
	private List<Rent> rents;
	
	@OneToMany(mappedBy = "customer")
	private List<Invoice> invoices;
}
