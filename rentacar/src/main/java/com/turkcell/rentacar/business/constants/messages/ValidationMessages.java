package com.turkcell.rentacar.business.constants.messages;

public class ValidationMessages {

	
	/******** ADDITIONAL SERVICE ********/

	public static final String ADDITIONAL_SERVICE_NAME_RULE = "Additional service name must be between {min} and {max} characters.";
	
	public static final String ADDITIONAL_SERVICE_DAILY_PRICE_RULE = "Daily price must be equal or greater than {value}.";
	
	public static final String ADDITIONAL_SERVICE_ID_RULE = "ID must be equal or greater than {value}.";
	
	
	/******** BRAND ********/

	public static final String BRAND_NAME_RULE = "Brand name must be between {min} and {max} characters.";
	
	public static final String BRAND_ID_RULE = "ID must be equal or greater than {value}.";
	
	
	/******** CAR ********/

	public static final String CAR_DAILY_PRICE_RULE = "Daily price must be equal or greater than {value}.";
	
	public static final String CAR_MODEL_YEAR_RULE = "Model year must be equal or greater than {value}.";
	
	public static final String CAR_DESCRIPTION_RULE = "Car description must be between {min} and {max} characters.";
	
	public static final String CAR_KILOMETER_RULE = "Kilometer must be equal or greater than {value}.";
	
	public static final String CAR_BRAND_ID_RULE = "Brand ID must be equal or greater than {value}.";
	
	public static final String CAR_COLOR_ID_RULE = "Color ID must be equal or greater than {value}.";
	
	public static final String CAR_BASE_CITY_ID_RULE = "Base city ID must be equal or greater than {value}.";
	
	public static final String CAR_ID_RULE = "ID must be equal or greater than {value}.";
	

	/******** CAR MAINTENANCE ********/

	public static final String CAR_MAINTENANCE_DESCRIPTION_RULE = "Car maintenance description must be between {min} and {max} characters.";
	
	public static final String CAR_MAINTENANCE_ID_RULE = "ID must be equal or greater than {value}.";
	
	
	/******** CITY ********/

	public static final String CITY_NAME_RULE = "City name must be between {min} and {max} characters.";
	
	public static final String CITY_ID_RULE = "ID must be equal or greater than {value}.";
	
	
	/******** COLOR ********/

	public static final String COLOR_NAME_RULE = "Color name must be between {min} and {max} characters.";
	
	public static final String COLOR_ID_RULE = "ID must be equal or greater than {value}.";
	
	
	/******** CORPORATE CUSTOMER ********/

	public static final String CORPORATE_CUSTOMER_ID_RULE = "ID must be equal or greater than {value}.";
	
	public static final String CORPORATE_CUSTOMER_EMAIL_RULE = "Please make sure you enter a correct email address.";
	
	public static final String CORPORATE_CUSTOMER_PASSWORD_RULE = "Password must be between {min} and {max} characters.";
	
	public static final String CORPORATE_CUSTOMER_COMPANY_NAME_RULE = "Company name must be between {min} and {max} characters.";
	
	public static final String CORPORATE_CUSTOMER_TAX_NUMBER_RULE = "Tax number must be an 11-digit number.";
	
	
	/******** CREDIT CARD ********/

	public static final String CREDIT_CARD_ID_RULE = "ID must be equal or greater than {value}.";
	
	public static final String CREDIT_CARD_CUSTOMER_ID_RULE = "Customer ID must be equal or greater than {value}.";
	
	public static final String CREDIT_CARD_NUMBER_RULE = "Invalid credit card number was entered. Please try again.";
	
	public static final String CREDIT_CARD_HOLDER_NAME_RULE = "Credit card holder name must be between {min} and {max} characters.";
	
	public static final String CREDIT_CARD_MONTH_RULE = "Expiration month value must be between 1 and 12.";
	
	public static final String CREDIT_CARD_YEAR_RULE = "Expiration year value must be between 2022 and 2100.";
	
	public static final String CREDIT_CARD_CVV_RULE = "CVV must be a three-digit number.";
	
	
	/******** DAMAGE ********/

	public static final String DAMAGE_ID_RULE = "ID must be equal or greater than {value}.";
	
	public static final String DAMAGE_DESCRIPTION_RULE = "Damage description must be greater than {min} characters.";
	
	public static final String DAMAGE_CAR_ID_RULE = "Car ID must be equal or greater than {value}.";
	
	
	/******** INVOICE ********/

	public static final String INVOICE_ID_RULE = "ID must be equal or greater than {value}.";
	
	public static final String INVOICE_NUMBER_RULE = "Invoice number must be between {min} and {max} characters.";
	
	public static final String INVOICE_RENT_ID_RULE = "Rent ID must be equal or greater than {value}.";
	
	
	/******** INDIVIDUAL CUSTOMER ********/

	public static final String INDIVIDUAL_CUSTOMER_ID_RULE = "ID must be equal or greater than {value}.";
	
	public static final String INDIVIDUAL_CUSTOMER_EMAIL_RULE = "Please make sure you enter a correct email address.";
	
	public static final String INDIVIDUAL_CUSTOMER_PASSWORD_RULE = "Password must be between {min} and {max} characters.";
	
	public static final String INDIVIDUAL_CUSTOMER_FIRST_NAME_RULE = "First name must be between {min} and {max} characters.";
	
	public static final String INDIVIDUAL_CUSTOMER_LAST_NAME_RULE = "Last name must be between {min} and {max} characters.";
	
	public static final String INDIVIDUAL_CUSTOMER_NATIONAL_IDENTITY_RULE = "National identity number must be an 11-digit number.";
	
	
	/******** ORDERED SERVICE ********/

	public static final String ORDERED_SERVICE_ID_RULE = "Additional service ID must be equal or greater than {value}.";
	
	public static final String ORDERED_SERVICE_AMOUNT_RULE = "Amount must be equal or greater than {value}.";

	
	/******** POS ********/
	
	public static final String POS_CREDIT_CARD_NUMBER_RULE = "Invalid credit card number was entered. Please try again.";
	
	public static final String POS_CREDIT_CARD_HOLDER_NAME_RULE = "Credit card holder name must be between {min} and {max} characters.";
	
	public static final String POS_CREDIT_CARD_MONTH_RULE = "Expiration month value must be between 1 and 12.";
	
	public static final String POS_CREDIT_CARD_YEAR_RULE = "Expiration year value must be between 2022 and 2100.";
	
	public static final String POS_CREDIT_CARD_CVV_RULE = "CVV must be a three-digit number.";
	
	
	/******** RENT ********/

	public static final String RENT_ID_RULE = "ID must be equal or greater than {value}.";
	
	public static final String RENT_CAR_ID_RULE = "Car ID must be equal or greater than {value}.";
	
	public static final String RENT_RENT_CITY_ID_RULE = "Rent city ID must be equal or greater than {value}.";
	
	public static final String RENT_RETURN_CITY_ID_RULE = "Return city ID must be equal or greater than {value}.";
	
	public static final String RENT_CUSTOMER_ID_RULE = "Customer ID must be equal or greater than {value}.";
	
	public static final String RENT_END_KILOMETER_RULE = "End kilometer must be equal or greater than {value}.";
}
