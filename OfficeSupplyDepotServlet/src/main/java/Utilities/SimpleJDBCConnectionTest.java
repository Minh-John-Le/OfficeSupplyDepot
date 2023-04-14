package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleJDBCConnectionTest {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/OfficeSupplyDepotDatabase?useSSL=false&useTimezone=true&serverTimezone=America/Los_Angeles";
        String dbUser = "root";
        String dbPassword = "!Changeme123";
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
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

