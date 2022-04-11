package com.turkcell.rentacar.business.dtos.city;

import java.util.List;

import com.turkcell.rentacar.business.dtos.car.CarDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityListDto {

	private int id;
	private String cityName;
	private List<CarDTO> cars;
	
}
