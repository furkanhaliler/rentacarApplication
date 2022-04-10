package com.turkcell.rentacar.core.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.buffer.DataBufferUtils.Matcher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.turkcell.rentacar.api.models.EnumSaveCreditCard;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.core.utilities.results.ErrorDataResult;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException (MethodArgumentNotValidException methodArgumentNotValidException){
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(validationErrors, "Validation Error.");
		return errorDataResult;
	}

	@ExceptionHandler({BusinessException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleBusinessException (BusinessException businessException){
		
		Map<String, String> validationErrors = new HashMap<String, String>();
		validationErrors.put(businessException.getMessage(), "Business Exception");
		
		ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(validationErrors, "Business Exception");
		return errorDataResult;
		
	}
	
//sor
	@ExceptionHandler({DataIntegrityViolationException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleDataException (DataIntegrityViolationException dataIntegrityViolationException){
		
		Map<String, String> validationErrors = new HashMap<String, String>();
		validationErrors.put(dataIntegrityViolationException.getRootCause().getMessage(), "Data Exception");
//		dataIntegrityViolationException.getClass().getSimpleName();
//		dataIntegrityViolationException.getMostSpecificCause().getMessage();
		ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(validationErrors, "Data Exception");
		return errorDataResult;
	}
	
}
