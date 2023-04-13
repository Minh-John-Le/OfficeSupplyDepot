package Utilities;

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
}
