package com.turkcell.rentacar.business.dtos.car;

import java.util.List;

import com.turkcell.rentacar.business.dtos.damage.DamageDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarListDto {

	private int id;
	private double dailyPrice;
	private int modelYear;
	private String description;
	private Double kilometer;
	private String brandName;
	private String colorName;
	private String baseCityName;
	private boolean rentStatus;
	private boolean maintenanceStatus;
	private List<DamageDTO> damages;
}
