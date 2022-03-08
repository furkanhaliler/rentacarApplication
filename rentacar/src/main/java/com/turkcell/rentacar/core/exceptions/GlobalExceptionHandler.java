package com.turkcell.rentacar.core.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
	public ErrorDataResult<Object> handleColorValidationException (BusinessException businessException){
		
		Map<String, String> validationErrors = new HashMap<String, String>();
		validationErrors.put(businessException.getMessage(), "Business.Exception");
		
		ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(validationErrors, "Validation Error.");
		return errorDataResult;
		
	}
	
	
}
