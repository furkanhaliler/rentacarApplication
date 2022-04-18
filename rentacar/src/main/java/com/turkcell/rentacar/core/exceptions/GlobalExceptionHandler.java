package com.turkcell.rentacar.core.exceptions;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

	@ExceptionHandler({DataIntegrityViolationException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleDataException (DataIntegrityViolationException dataIntegrityViolationException){
		
		Map<String, String> validationErrors = new HashMap<String, String>();
		validationErrors.put(BusinessMessages.DATA_INTEGRITY_VIOLATION_EXCEPTION, "Data Exception");

		ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(validationErrors, "Data Exception");
		return errorDataResult;
	}

	@ExceptionHandler({DateTimeParseException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleDataException (DateTimeParseException dateTimeParseException){
			
		Map<String, String> validationErrors = new HashMap<String, String>();
		validationErrors.put(BusinessMessages.TIME_FORMAT_EXCEPTION, "Time format exception");

		ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(validationErrors, "Time format exception");
		return errorDataResult;
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleDataException (HttpMessageNotReadableException httpMessageNotReadableException){
			
		Map<String, String> validationErrors = new HashMap<String, String>();
		validationErrors.put(BusinessMessages.HTTP_MESSAGE_NOT_READABLE_EXCEPTION, "Http message not readable");

		ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(validationErrors, "Http message not readable");
		return errorDataResult;
	}
	
}
