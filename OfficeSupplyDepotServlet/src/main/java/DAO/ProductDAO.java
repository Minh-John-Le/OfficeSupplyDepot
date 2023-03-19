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
	
	private void deleteProduct(Product product) {
		return;
	}
	
	private void updateProduct(Product product) {
		return;
	}
	
	
}