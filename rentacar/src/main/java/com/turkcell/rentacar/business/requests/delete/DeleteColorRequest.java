package com.turkcell.rentacar.business.requests.delete;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeleteColorRequest {
	
	@NotNull
	@Min(1)
    private int colorId;
}
