package com.turkcell.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.turkcell.rentacar.business.dtos.*;
import com.turkcell.rentacar.business.requests.*;
import com.turkcell.rentacar.business.requests.create.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.delete.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.update.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CarMaintenanceService {

	DataResult<List<CarMaintenanceListDto>> getAll() throws BusinessException;
	Result add (CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException;
	DataResult<List<CarMaintenanceListDto>> getByCarId(Integer id) throws BusinessException;
	Result update (UpdateCarMaintenanceRequest updateCarMaintenanceRequest) throws BusinessException;
	Result delete (DeleteCarMaintenanceRequest deleteCarMaintenanceRequest)throws BusinessException;
	DataResult<List<CarMaintenanceListDto>> getOnMaintenanceCars();
//	boolean checkIfCarIsInMaintenance(int id, LocalDate localDate);
    boolean checkIfCarIsInMaintenance(int id);
			
}
