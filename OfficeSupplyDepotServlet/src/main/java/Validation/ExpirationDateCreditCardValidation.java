package Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ExpirationDateCreditCardValidation {
	private static final Pattern QUOTES_PATTERN = Pattern.compile("['\"]");
	
	public static boolean isValid(Object value) {
        // Parse the expiration date string into a LocalDate object
		if (value == null || !(value instanceof String)) {
			return false;
		}
		// "DD/DD" length 5 
		String expirationDate = (String) value;
		if (QUOTES_PATTERN.matcher(expirationDate).find()) {
			return false;
	    }
		if(expirationDate.length() != 5) {
			return false;
		}
        LocalDate expDate;
        try {
        	// Assume that credit cards expire on the first of the month. Easier this way
        	// Don't have to calculate last day of month.
            expDate = LocalDate.parse("01/" + expirationDate, DateTimeFormatter.ofPattern("dd/MM/yy"));
        } catch (DateTimeParseException e) {
            // If the date string cannot be parsed, return false
            return false;
        }
        
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Check if the expiration date has passed or not
        return expDate.isAfter(currentDate) || expDate.isEqual(currentDate);
    }
	
	// ONLY TO BE CALLED ON INVALID Expiration Dates
	public static ArrayList<String> getIssues(Object value) {
		String expirationDate = (String) value;
        ArrayList<String> issues = new ArrayList<String>();
        if (expirationDate == null || expirationDate.equals("")) {
            issues.add("Expiration date is empty");
            return issues;
        }
        if (QUOTES_PATTERN.matcher(expirationDate).find()) {
        	issues.add("Expiration date contains quotes.");
        }
        try {
            LocalDate.parse("01/" + expirationDate, DateTimeFormatter.ofPattern("dd/MM/yy"));
        } catch (DateTimeParseException e) {
            issues.add("Invalid expiration date format");
        }
        if (!isValid(expirationDate)) {
            issues.add("Credit card has expired");
        }
        return issues;
    }

}
