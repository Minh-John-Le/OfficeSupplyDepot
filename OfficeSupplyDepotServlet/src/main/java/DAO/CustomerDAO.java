package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.Customer;


public class CustomerDAO {
    
    private String url ="";
    private String mySQLUser = "";
    private String mySQLPass = "";
    
    public CustomerDAO(String url, String user, String password) 
    {
    	this.url = url;
    	this.mySQLUser = user;
    	this.mySQLPass = password;
    }
    
    public void addCustomer(Customer customer)  {
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "INSERT INTO Customers (CustomerName, Username, Password, Email, Address) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, customer.getCustomerName());
	        statement.setString(2, customer.getUsername());
	        statement.setString(3, customer.getPassword());
	        statement.setString(4, customer.getEmail());
	        statement.setString(5, customer.getAddress());
	        statement.executeUpdate();
	        statement.close();
	        connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    public void deleteCustomer(int customerId) {
    	
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
		 	String query = "DELETE FROM Customers WHERE Id = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, customerId);
	        statement.executeUpdate();
	        statement.close();
	        connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    public void updateCustomer(Customer customer) {
    
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "UPDATE Customers SET CustomerName=?, Username=?, Password=?, Email=?, Address=? WHERE Id=?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, customer.getCustomerName());
	        statement.setString(2, customer.getUsername());
	        statement.setString(3, customer.getPassword());
	        statement.setString(4, customer.getEmail());
	        statement.setString(5, customer.getAddress());
	        statement.setInt(6, customer.getId());
	        statement.executeUpdate();
	        statement.close();
	        connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Customer getCustomerById(int customerId) {
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "SELECT * FROM Customers WHERE Id = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, customerId);
	        ResultSet resultSet = statement.executeQuery();
	        Customer customer = null;
	        if(resultSet.next()) {
	            customer = new Customer();
	            customer.setId(resultSet.getInt("Id"));
	            customer.setCustomerName(resultSet.getString("CustomerName"));
	            customer.setUsername(resultSet.getString("Username"));
	            customer.setPassword(resultSet.getString("Password"));
	            customer.setEmail(resultSet.getString("Email"));
	            customer.setAddress(resultSet.getString("Address"));
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        return customer;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    public Customer getCustomerByUsername(String username) {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM Customers WHERE Username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            Customer customer = null;
            if (resultSet.next()) {
                customer = new Customer();
                customer.setId(resultSet.getInt("Id"));
                customer.setCustomerName(resultSet.getString("CustomerName"));
                customer.setUsername(resultSet.getString("Username"));
                customer.setPassword(resultSet.getString("Password"));
                customer.setEmail(resultSet.getString("Email"));
                customer.setAddress(resultSet.getString("Address"));
            }
            resultSet.close();
            statement.close();
            connection.close();
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Customer> getAllCustomers() {
    	
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "SELECT * FROM Customers";
	        PreparedStatement statement = connection.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        List<Customer> customers = new ArrayList<>();
	        while(resultSet.next()) {
	            Customer customer = new Customer();
	            customer.setId(resultSet.getInt("Id"));
	            customer.setCustomerName(resultSet.getString("CustomerName"));
	            customer.setUsername(resultSet.getString("Username"));
	            customer.setPassword(resultSet.getString("Password"));
	            customer.setEmail(resultSet.getString("Email"));
	            customer.setAddress(resultSet.getString("Address"));
	            customers.add(customer);
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        return customers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;     
    }
    
}
