package com.turkcell.rentacar.business.dtos.lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandListDto {
	
    private int id;
    private String brandName;
}
