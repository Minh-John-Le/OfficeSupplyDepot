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

	public boolean isValidPassword(String password) {
		if (password == null) {
            return false;
        }
		
		
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
	}
	
    public static ArrayList<String> getPasswordIssues(String password) {
        ArrayList<String> issues = new ArrayList<String>();
        if (password == null) {
            issues.add("Password cannot be null.");
            return issues;
        }
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            if (password.length() < 8 || password.length() > 20) {
                issues.add("Password length must be between 8 and 20 characters.");
            }
            if (!password.matches(".*\\d.*")) {
                issues.add("Password must contain at least one digit.");
            }
            if (!password.matches(".*[a-z].*")) {
                issues.add("Password must contain at least one lowercase letter.");
            }
            if (!password.matches(".*[A-Z].*")) {
                issues.add("Password must contain at least one uppercase letter.");
            }
            if (!password.matches(".*[@#$%].*")) {
                issues.add("Password must contain at least one of the following symbols: @, #, $, %.");
            }
        }
        return issues;
    }
    
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
	
    public static boolean isValidCreditCardNumber(String cardNumber) {
        // Remove any spaces and dashes from the input string
        cardNumber = cardNumber.replaceAll("[ -]", "");

        // Check if the input string contains only digits
        if (!cardNumber.matches("\\d+")) {
            return false;
        }

        // Check if the input string has a valid length for a credit card number
        int length = cardNumber.length();
        if (length < 13 || length > 19) {
            return false;
        }

        // Calculate the checksum using the Luhn algorithm
        int sum = 0;
        for (int i = length - 1; i >= 0; i--) {
            int digit = Integer.parseInt(cardNumber.substring(i, i + 1));
            if ((length - i) % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        return sum % 10 == 0;
    }
    
    public static ArrayList<String> getCreditCardIssues(String creditCardNumber) {
        ArrayList<String> issues = new ArrayList<String>();
        
        // Check for letters, spaces, or non-digits in the credit card number
        Pattern pattern = Pattern.compile("[^\\d]+");
        Matcher matcher = pattern.matcher(creditCardNumber);
        if (matcher.find()) {
            issues.add("Credit card number should only contain digits");
        }
        
        // Check for other credit card number validation issues and add them to the issues ArrayList as needed
        if (!isValidCreditCardNumber(creditCardNumber)) {
            issues.add("Credit card number is invalid");
        }
        
        return issues;
    }

		
	public boolean isNumeric(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length <= 0 || length > 20) {
	        return false;
	    }
	    for (int i = 0; i < length; i++) {
	        if (!Character.isDigit(str.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
	}
	
	public boolean isPNG(Part filePart) {
	    if (filePart == null || filePart.getSize() == 0) {
	        return false;
	    }
	    String contentType = filePart.getContentType();
	    return contentType != null && contentType.toLowerCase().startsWith("image/png");
	}
		
	public static boolean isValidDisplayName(String displayName) {
	    if (displayName == null || displayName.isEmpty()) {
	        return false;
	    }
	    if (displayName.length() > 20) {
	        return false;
	    }
	    if (displayName.contains(" ")) {
	        return false;
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
