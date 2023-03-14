package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.Store;

import java.sql.*;
import java.util.*;

public class StoreDAO {
    
    private String url ="";
    private String mySQLUser = "";
    private String mySQLPass = "";
    
    public StoreDAO(String url, String user, String password) 
    {
    	this.url = url;
    	this.mySQLUser = user;
    	this.mySQLPass = password;
    }
    
    public void addStore(Store store)  {
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "INSERT INTO Stores (StoreName, Username, Password, Email) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, store.getStoreName());
	        statement.setString(2, store.getUsername());
	        statement.setString(3, store.getPassword());
	        statement.setString(4, store.getEmail());
	        statement.executeUpdate();
	        statement.close();
	        connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    public void deleteStore(int storeId) {
    	
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
		 	String query = "DELETE FROM Stores WHERE Id = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, storeId);
	        statement.executeUpdate();
	        statement.close();
	        connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    public Store getStoreById(int storeId) {
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "SELECT * FROM Stores WHERE Id = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, storeId);
	        ResultSet resultSet = statement.executeQuery();
	        Store store = null;
	        if(resultSet.next()) {
	            store = new Store();
	            store.setId(resultSet.getInt("Id"));
	            store.setStoreName(resultSet.getString("StoreName"));
	            store.setUsername(resultSet.getString("Username"));
	            store.setPassword(resultSet.getString("Password"));
	            store.setEmail(resultSet.getString("Email"));
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        return store;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    public Store getStoreByUsername(String username) {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM Stores WHERE Username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            Store store = null;
            if (resultSet.next()) {
                store = new Store();
                store.setId(resultSet.getInt("Id"));
                store.setStoreName(resultSet.getString("StoreName"));
                store.setUsername(resultSet.getString("Username"));
                store.setPassword(resultSet.getString("Password"));
                store.setEmail(resultSet.getString("Email"));
            }
            resultSet.close();
            statement.close();
            connection.close();
            return store;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<Store> getAllStores() {
    	
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "SELECT * FROM Stores";
	        PreparedStatement statement = connection.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        List<Store> stores = new ArrayList<>();
	        while(resultSet.next()) {
	            Store store = new Store();
	            store.setId(resultSet.getInt("id"));
	            store.setStoreName(resultSet.getString("StoreName"));
	            store.setUsername(resultSet.getString("Username"));
	            store.setPassword(resultSet.getString("Password"));
	            store.setEmail(resultSet.getString("Email"));
	            stores.add(store);
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        return stores;
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
