package Validation;

import java.util.ArrayList;

public class StringValidation {
    public static final int MAX_SIZE = 255;

    public static boolean isValid(Object value) {
        if (value == null || !(value instanceof String)) {
            return false;
        }
        String str = (String) value;
        if (str.isEmpty() || str.length() > MAX_SIZE) {
            return false;
        }
        return true;
    }

    public static ArrayList<String> getIssues(Object value) {
        ArrayList<String> issues = new ArrayList<String>();
        if (value == null || !(value instanceof String)) {
            issues.add("Input value must be a non-empty string.");
            return issues;
        }
        String str = (String) value;
        if (str.isEmpty()) {
            issues.add("Input string cannot be empty.");
        }
        if (str.length() > MAX_SIZE) {
            issues.add("Input string length must be less than or equal to " + MAX_SIZE + " characters.");
        }
        return issues;
    }
}
