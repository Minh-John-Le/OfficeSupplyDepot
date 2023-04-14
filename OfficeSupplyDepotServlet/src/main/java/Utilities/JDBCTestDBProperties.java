package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTestDBProperties {
    public static void main(String[] args) {
    	String cwd = System.getProperty("user.dir");
        System.out.println("Current working directory: " + cwd);
        Properties props = new Properties();
        FileInputStream fis = null;
        
        try {
            fis = new FileInputStream("./src/main/webapp/WEB-INF/classes/db.properties");
            props.load(fis);
            System.out.println("GOT HERE");
        } catch (IOException e) {
            System.err.println("Error loading database properties: " + e.getMessage());
            return;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // Ignore exception
                }
            }
        }
        
        String dbDriver = props.getProperty("jdbc.driver");
        String dbUrl = props.getProperty("jdbc.url");
        String dbUser = props.getProperty("jdbc.username");
        String dbPassword = props.getProperty("jdbc.password");
        
        try {
            // Load the MySQL JDBC driver
            Class.forName(dbDriver);
            
            // Connect to the database
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            
            // Print a success message
            System.out.println("Managed to connect to the database!");
            
            // Close the connection
            conn.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading MySQL JDBC driver: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }
}
