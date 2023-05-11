package Validation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation implements validation {
	public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
	@Override
	public boolean isValid(Object value) {
		
		if(!(value instanceof String) || value == null) {
			return false;
		}
		
		String password = (String) value; 
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
	}

	@Override
	public ArrayList<String> getIssues(Object value) {
		ArrayList<String> issues = new ArrayList<String>();
		
		if(!(value instanceof String) || value == null) {
			issues.add("Password cannot be null.");
			return issues;
		}
		String password = (String) value;
		
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
}
