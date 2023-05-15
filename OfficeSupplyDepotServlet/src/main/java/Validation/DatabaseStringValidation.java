package Validation;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DatabaseStringValidation {
	private static final Pattern QUOTES_PATTERN = Pattern.compile("['\"]");
	
	public static boolean isValid(Object value, int maxLength) {
        if (value == null || !(value instanceof String)) {
            return false;
        }
        String strValue = (String) value;
        // Check if the value contains any quotes
        if (QUOTES_PATTERN.matcher(strValue).find()) {
            return false;
        }

        return !strValue.isEmpty() && strValue.length() <= maxLength;
    }
	
	public static ArrayList<String> getIssues(Object value, int maxLength, String stringName) {
	    ArrayList<String> issues = new ArrayList<>();
	
	    if (value == null) {
	        issues.add(stringName + " cannot be null");
	    } else if (!(value instanceof String)) {
	        issues.add(stringName + " is not a string");
	    } else {
	        String strValue = (String) value;
	        if (QUOTES_PATTERN.matcher(strValue).find()) {
	        	issues.add(stringName + " contains invalid characters");
	        }
	        if (strValue.isEmpty()) {
	            issues.add(stringName + " cannot be empty");
	        }
	
	        if (strValue.length() > maxLength) {
	            issues.add(stringName + " length must be less than or equal to " + maxLength);
	        }
	    }
	
	    return issues;
	}
}
