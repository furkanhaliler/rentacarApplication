package com.turkcell.rentacar.core.exceptions;

public class BrandAlreadyExistException extends BusinessException {
    public BrandAlreadyExistException(String message) {
    	super(message);
    }
}
