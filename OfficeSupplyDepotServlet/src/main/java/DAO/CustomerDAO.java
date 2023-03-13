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
    
    private Connection connection;
    
    public CustomerDAO(String url, String user, String password) throws SQLException {
    	if (connection == null || connection.isClosed()) {
           
            connection = DriverManager.getConnection(url, user, password);
        }
    }
    
    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO customer (customer_name, username, password, email) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, customer.getCustomerName());
        statement.setString(2, customer.getUsername());
        statement.setString(3, customer.getPassword());
        statement.setString(4, customer.getEmail());
        statement.executeUpdate();
        statement.close();
    }
    
    public void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM customer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, customerId);
        statement.executeUpdate();
        statement.close();
    }
    
    public void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE customer SET customer_name=?, username=?, password=?, email=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, customer.getCustomerName());
        statement.setString(2, customer.getUsername());
        statement.setString(3, customer.getPassword());
        statement.setString(4, customer.getEmail());
        statement.setInt(5, customer.getId());
        statement.executeUpdate();
        statement.close();
    }
    
    public Customer getCustomerById(int customerId) throws SQLException {
        String query = "SELECT * FROM customer WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, customerId);
        ResultSet resultSet = statement.executeQuery();
        Customer customer = null;
        if(resultSet.next()) {
            customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setCustomerName(resultSet.getString("customer_name"));
            customer.setUsername(resultSet.getString("username"));
            customer.setPassword(resultSet.getString("password"));
            customer.setEmail(resultSet.getString("email"));
        }
        resultSet.close();
        statement.close();
        return customer;
    }
    
    public List<Customer> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM customer";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Customer> customers = new ArrayList<>();
        while(resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setCustomerName(resultSet.getString("customer_name"));
            customer.setUsername(resultSet.getString("username"));
            customer.setPassword(resultSet.getString("password"));
            customer.setEmail(resultSet.getString("email"));
            customers.add(customer);
        }
        resultSet.close();
        statement.close();
        return customers;
    }
    
    public void close() throws SQLException {
        connection.close();
    }
}