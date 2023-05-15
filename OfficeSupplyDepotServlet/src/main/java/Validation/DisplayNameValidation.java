package Validation;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class DisplayNameValidation{
	private static final Pattern QUOTES_PATTERN = Pattern.compile("['\"]");
	
	public static boolean isValid(Object value) {
		
	    if (value == null || !(value instanceof String)) {
	        return false;
	    }
	    
	    String displayName = (String) value;
	    if (QUOTES_PATTERN.matcher(displayName).find()) {
	    	return false;
	    }
	    
	    if (displayName.length() > 50) {
	        return false;
	    }
	    if (displayName.contains(" ")) {
	        return false;
	    }
	    return true;
	}

	public static ArrayList<String> getIssues(Object value) {
		ArrayList<String> issues = new ArrayList<String>();
		if(!(value instanceof String) || value == null) {
			issues.add("Display Name cannot be null.");
		}
		
		String displayName = (String) value;
		if (QUOTES_PATTERN.matcher(displayName).find()) {
	    	issues.add(displayName + " contains invalid characters");
	    }
	    if (displayName.isEmpty()) {
	        issues.add("Display name cannot be empty.");
	    }
	    if (displayName.length() > 20) {
	        issues.add("Display name length must be less than or equal to 20 characters.");
	    }
	    if (displayName.contains(" ")) {
	        issues.add("Display name cannot contain spaces.");
	    }
		
		return issues;
	}

}
