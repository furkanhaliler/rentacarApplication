package com.turkcell.rentacar.business.dtos.gets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetColorDto {
	
    private int id;
    private String colorName;
}
