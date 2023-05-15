package Validation;

import java.util.ArrayList;

public interface validation {
	boolean isValid(Object value);
	ArrayList<String> getIssues(Object value);
}
