package com.turkcell.rentacar.entities.concretes;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_maintenances")
public class CarMaintenance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "maintenance_id")
	private int maintenanceId;
	
	@Column(name = "maintenance_description")
	private String maintenanceDescription;
	
	@Column(name = "maintenance_returnDate")
    private LocalDate returnDate;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	
	
	
}
