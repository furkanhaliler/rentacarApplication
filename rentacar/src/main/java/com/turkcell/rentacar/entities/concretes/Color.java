package com.turkcell.rentacar.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "colors")
public class Color {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "color_id")
	private int id;
	
	@Column(name = "color_name")
	private String colorName;
	
	@OneToMany(mappedBy = "color")
	private List<Car> cars;
}
