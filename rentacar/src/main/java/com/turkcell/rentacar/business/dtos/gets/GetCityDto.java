package com.turkcell.rentacar.business.dtos.gets;

import java.util.List;

import com.turkcell.rentacar.business.dtos.lists.CarListDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetCityDto {

	private int id;
	private String cityName;
	private List<CarListDto> cars;

}
