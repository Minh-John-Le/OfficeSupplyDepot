package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Beans.Product;

	
public class ProductDAO{
    private String url = "";
    private String mySQLUser = "";
    private String mySQLPass = "";
    
	public ProductDAO(String url, String user, String password) 
	{
		this.url = url;
    	this.mySQLUser = user;
    	this.mySQLPass = password;
	}
		
	
	private void addProduct(Product product ) {
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "INSERT INTO Products (Name, Stock, Weight, Description, Price, ImageURL) VALUES (?, ?, ?, ?, ?,?)";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, product.getName());
	        statement.setInt(2, product.getStock());
	        statement.setBigDecimal(3, product.getWeight());
	        statement.setString(4, product.getDescription());
	        statement.setBigDecimal(5, product.getPrice());
	        statement.setString(6, product.getImageURL());
	        statement.executeUpdate();
	        statement.close();
	        connection.close();
		}
		
		
		
	}
	
	private void deleteProduct(int productId) {

    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
		 	String query = "DELETE FROM Products WHERE Id = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, productId);
	        statement.executeUpdate();
	        statement.close();
	        connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		return;
	}
	
	private void updateProduct(Product product) {
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "UPDATE Products SET Name=?, Stock=?, Weight=?, Description=?, Price=?, ImageURL=?, WHERE Id=?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, product.getName());
	        statement.setInt(2, product.getStock());
	        statement.setBigDecimal(3, product.getWeight());
	        statement.setString(4, product.getDescription());
	        statement.setBigDecimal(5, product.getPrice());
	        statement.setString(6, product.getImageURL());
	        statement.setInt(7,product.getId());
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
		return;
	}
	
	
}