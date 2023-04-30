package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Beans.Customer;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class CustomerDAO {
	
	private DataSource dataSource;
	private String url  = "";
	private String mySQLUser  = "";
	private String mySQLPass = "";
	
	
    public CustomerDAO(String url, String user, String password) 
    {
    	this.url = url;
    	this.mySQLUser = user;
    	this.mySQLPass = password;
    	dataSource = getDataSource();
    }
    
    private DataSource getDataSource() {
        if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(url);
            ds.setUsername(mySQLUser);
            ds.setPassword(mySQLPass);
            ds.setMinIdle(1);
            ds.setMaxIdle(1);
            ds.setMaxTotal(1);
            dataSource = ds;
        }
        return dataSource;
    }
    
    public void addCustomer(Customer customer)  {
    	try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO Customers (CustomerName, Username, Password, Email, Address) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, customer.getCustomerName());
                statement.setString(2, customer.getUsername());
                statement.setString(3, customer.getPassword());
                statement.setString(4, customer.getEmail());
                statement.setString(5, customer.getAddress());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }
    
    public void deleteCustomer(int customerId) {
    	try (Connection connection = dataSource.getConnection()) {
            String query = "DELETE FROM Customers WHERE Id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, customerId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateCustomer(Customer customer) {
    	try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE Customers SET CustomerName=?, Username=?, Password=?, Email=?, Address=? WHERE Id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, customer.getCustomerName());
                statement.setString(2, customer.getUsername());
                statement.setString(3, customer.getPassword());
                statement.setString(4, customer.getEmail());
                statement.setString(5, customer.getAddress());
                statement.setInt(6, customer.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Customer getCustomerById(int customerId) {
    	try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Customers WHERE Id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, customerId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Customer customer = new Customer();
                        customer.setId(resultSet.getInt("Id"));
                        customer.setCustomerName(resultSet.getString("CustomerName"));
                        customer.setUsername(resultSet.getString("Username"));
                        customer.setPassword(resultSet.getString("Password"));
                        customer.setEmail(resultSet.getString("Email"));
                        customer.setAddress(resultSet.getString("Address"));
                        return customer;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Customer getCustomerByUsername(String username) {
	    try (Connection connection = dataSource.getConnection()) {
	        String query = "SELECT * FROM Customers WHERE Username = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, username);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    Customer customer = new Customer();
	                    customer.setId(resultSet.getInt("Id"));
	                    customer.setCustomerName(resultSet.getString("CustomerName"));
	                    customer.setUsername(resultSet.getString("Username"));
	                    customer.setPassword(resultSet.getString("Password"));
	                    customer.setEmail(resultSet.getString("Email"));
	                    customer.setAddress(resultSet.getString("Address"));
	                    return customer;
	                }
	            }
	        }
	    } catch (SQLException ex) {
	        System.err.println("Error getting customer by username: " + ex.getMessage());
	    }
	    return null;
	}
    
    public List<Customer> getAllCustomers() {
    	try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Customers";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
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
                    return customers;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
