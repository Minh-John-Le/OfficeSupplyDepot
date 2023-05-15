package Validation;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class EmailValidation {
	private static final Pattern QUOTES_PATTERN = Pattern.compile("['\"]");
	
    public static boolean isValid(Object value) {
        if (value == null || !(value instanceof String)) {
            return false;
        }

        String email = (String) value;
        if (QUOTES_PATTERN.matcher(email).find()) {
        	return false;
        }
        // Perform basic email validation
        if (!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            return false;
        }
        
        return true;
    }

    public static ArrayList<String> getIssues(Object value) {
        ArrayList<String> issues = new ArrayList<String>();

        if (!(value instanceof String) || value == null) {
            issues.add("Email address cannot be null.");
            return issues;
        }

        String email = (String) value;
        if (QUOTES_PATTERN.matcher(email).find()) {
        	issues.add("email contains quotes.");
        }
        if (email.isEmpty()) {
            issues.add("Email address cannot be empty.");
        }

        if (!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
            issues.add("Invalid email address format.");
        }

        return issues;
    }

}
