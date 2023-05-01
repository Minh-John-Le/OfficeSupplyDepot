package Utilities;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;
import java.lang.System;


public class Settings {
	private static int current = 0;
	
	public static final String DEVELOPER_DB_PROPERTIES = "/WEB-INF/classes/docker-db.properties";
	public static final String PRODUCTION_DB_PROPERTIES = "/WEB-INF/classes/db.properties";
    public final String FIRST_DAY = "1970-01-01";

//    If using the development branch, return db.properties path and
//    If using the deployment branch, return docker-db.properties path.
	public static String getPropertyFile() {
//	    System.out.println(System.getProperty("user.dir"));
	    if (current == 0) {
	        return DEVELOPER_DB_PROPERTIES;
	    } else if (current == 1) {
	        return PRODUCTION_DB_PROPERTIES;
	    } else {
	        throw new RuntimeException("Unknown branch");
	    }
	}
	
	public static Properties getProperties(ServletContext context) {
		// Get the input stream for the properties file
    	InputStream input = null ;        
    	String propertiesFile = Settings.getPropertyFile(); // assuming the method is defined in Utilities.Settings class
        input = context.getResourceAsStream(propertiesFile);
        // Load the properties from the file
        Properties props = new Properties();
		try {
			props.load(input);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return props;
	}
	
	
	public static void main(String[] args) {
		System.out.println(Settings.getPropertyFile());
	}
}
