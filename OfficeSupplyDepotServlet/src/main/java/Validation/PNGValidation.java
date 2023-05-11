package Validation;

import java.util.ArrayList;

import javax.servlet.http.Part;

import javax.servlet.http.Part;

public class PNGValidation {
	public static boolean isValid(Object value) {
		Part filePart = (Part) value;
		if (filePart == null || filePart.getSize() == 0) {
	        return false;
	    }
		String contentType = filePart.getContentType();
	    return contentType != null && contentType.toLowerCase().startsWith("image/png");
	}
	
	
	
	public static ArrayList<String> getIssues(Object value) {
		ArrayList<String> issues = new ArrayList<String>();
		
		if(!(value instanceof String) || value == null) {
			issues.add("Password cannot be null.");
			return issues;
		}

}
