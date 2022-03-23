package com.turkcell.rentacar.business.dtos.gets;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarDto {
	
    private int id;
    private double dailyPrice;
    private int modelYear;
    private String description;
    private Double kilometer;
    private String brandName;
    private String colorName;
    private String baseCityName;
    private List<GetDamageDto> damages;
}
