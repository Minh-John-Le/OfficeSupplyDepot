package Utilities;

import java.util.Calendar;

public class ValidationUtil {
	public boolean isValidPassword(String password) {
	    if (password == null || password.length() < 8) {
	        return false;
	    }
	    boolean hasUpperCase = false;
	    boolean hasLowerCase = false;
	    boolean hasDigit = false;
	    boolean hasSpecialChar = false;
	    for (int i = 0; i < password.length(); i++) {
	        char ch = password.charAt(i);
	        if (Character.isUpperCase(ch)) {
	            hasUpperCase = true;
	        } else if (Character.isLowerCase(ch)) {
	            hasLowerCase = true;
	        } else if (Character.isDigit(ch)) {
	            hasDigit = true;
	        } else if (!Character.isWhitespace(ch)) {
	            hasSpecialChar = true;
	        }
	    }
	    return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
	}
	
	public boolean isValidExpDate(String expDate) {
	    // Check if the expiry date is in the format MM/YY
	    if (!expDate.matches("^\\d{2}/\\d{2}$")) {
	        return false;
	    }

	    // Split the expiry date into month and year
	    String[] parts = expDate.split("/");
	    int month = Integer.parseInt(parts[0]);
	    int year = Integer.parseInt(parts[1]);

	    // Check if the month is between 1 and 12
	    if (month < 1 || month > 12) {
	        return false;
	    }

	    // Get the current year and month
	    Calendar now = Calendar.getInstance();
	    int currentYear = now.get(Calendar.YEAR) % 100; // Get the last 2 digits of the year
	    int currentMonth = now.get(Calendar.MONTH) + 1; // January is 0, so add 1

	    // Check if the expiry year is greater than or equal to the current year
	    if (year < currentYear) {
	        return false;
	    }

	    // If the expiry year is the same as the current year, check if the expiry month is greater than or equal to the current month
	    if (year == currentYear && month < currentMonth) {
	        return false;
	    }

	    return true;
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
}
