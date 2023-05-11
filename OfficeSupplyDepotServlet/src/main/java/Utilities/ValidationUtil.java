package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.servlet.http.Part;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";

	
    public boolean isValidExpDate(String expirationDate) {
        // Parse the expiration date string into a LocalDate object
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
    
	public boolean isNumeric(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length <= 0 || length > 20)
	    {
	        return false;
	    }
	    for (int i = 0; i < length; i++) {
	        if (!Character.isDigit(str.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
	}

	
	public boolean isValidBarcode(String barcode) {
	    if (barcode == null || barcode.isEmpty()) {
	        return false; // barcode is empty or null
	    }
	    
	    // Check if the barcode has more than 20 characters
	    if (barcode.length() > 20) {
	        return false;
	    }
	    
	    // Check if the barcode contains only letters and digits
	    if (!barcode.matches("[a-zA-Z0-9]+")) {
	        return false;
	    }
	    
	    return true;
	}

}
