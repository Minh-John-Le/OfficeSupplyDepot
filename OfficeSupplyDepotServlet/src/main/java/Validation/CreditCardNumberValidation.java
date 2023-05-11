package Validation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreditCardNumberValidation{

	public static boolean isValid(Object value) {
		if(!(value instanceof String) || value == null) {
			return false;
		}
		
		String cardNumber = (String) value;
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

	public static ArrayList<String> getIssues(Object value) {
		ArrayList<String> issues = new ArrayList<String>();
		if (!(value instanceof String) || value == null) {
			issues.add("Credit card number is empty");
			return issues;
		}
		String creditCardNumber = (String) value;
        
        // Check for letters, spaces, or non-digits in the credit card number
        Pattern pattern = Pattern.compile("[^\\d]+");
        Matcher matcher = pattern.matcher(creditCardNumber);
        if (matcher.find()) {
            issues.add("Credit card number should only contain digits");
        }
        
        // Check for other credit card number validation issues and add them to the issues ArrayList as needed
        if (!isValid(creditCardNumber)) {
            issues.add("Credit card number is invalid");
        }
        
        return issues;
	}

}
