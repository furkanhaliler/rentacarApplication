package com.turkcell.rentacar.business.dtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetBrandDto {
	
    private int id;
    private String brandName;
}
