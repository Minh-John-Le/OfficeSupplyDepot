package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
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
		
	
	public void addProduct(Product product) {
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "INSERT INTO Products (Name, Stock, Weight, Description, Price, ImageURL, Warehouse_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, product.getName());
	        statement.setInt(2, product.getStock());
	        statement.setBigDecimal(3, product.getWeight());
	        statement.setString(4, product.getDescription());
	        statement.setBigDecimal(5, product.getPrice());
	        statement.setString(6, product.getImageURL());
	        statement.setInt(7, product.getWarehouse_id());
	        statement.executeUpdate();
	        statement.close();
	        connection.close();
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public void deleteProduct(int productId) {

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
	
	public void updateProduct(Product product) {
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "UPDATE Products SET Name=?, Stock=?, Weight=?, Description=?, Price=?, ImageURL=?, Warehouse_ID = ? WHERE Id=?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, product.getName());
	        statement.setInt(2, product.getStock());
	        statement.setBigDecimal(3, product.getWeight());
	        statement.setString(4, product.getDescription());
	        statement.setBigDecimal(5, product.getPrice());
	        statement.setString(6, product.getImageURL());
	        statement.setInt(7, product.getWarehouse_id());
	        statement.setInt(8,product.getId());
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
	
	public Product getProductByName(String productName) {
	    Connection connection;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
	        String query = "SELECT * FROM Products WHERE Name = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, productName);
	        ResultSet resultSet = statement.executeQuery();
	        Product product = null;
	        if (resultSet.next()) {
	            product = new Product();
	            product.setId(resultSet.getInt("Id"));
	            product.setName(resultSet.getString("Name"));
	            product.setStock(resultSet.getInt("Stock"));
	            product.setWeight(resultSet.getBigDecimal("Weight"));
	            product.setDescription(resultSet.getString("Description"));
	            product.setPrice(resultSet.getBigDecimal("Price"));
	            product.setImageURL(resultSet.getString("ImageURL"));
	            product.setWarehouse_id(resultSet.getInt("Warehouse_ID"));
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        return product;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public List<Product> searchProductsByName(String searchTerm) {
	    List<Product> products = new LinkedList<>();
	    Connection connection;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
	        String query = "SELECT * FROM Products WHERE Name LIKE ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, searchTerm + "%");
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            Product product = new Product();
	            product.setId(resultSet.getInt("Id"));
	            product.setName(resultSet.getString("Name"));
	            product.setStock(resultSet.getInt("Stock"));
	            product.setWeight(resultSet.getBigDecimal("Weight"));
	            product.setDescription(resultSet.getString("Description"));
	            product.setPrice(resultSet.getBigDecimal("Price"));
	            product.setImageURL(resultSet.getString("ImageURL"));
	            product.setWarehouse_id(resultSet.getInt("Warehouse_ID"));
	            products.add(product);
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        return products;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
}