package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import beans.CartItem;
import beans.Product;

	
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
			String query = "INSERT INTO Products (Name, Stock, Weight, Description, Price, ImageURL, Warehouse_ID, Category, Barcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, product.getName());
	        statement.setInt(2, product.getStock());
	        statement.setBigDecimal(3, product.getWeight());
	        statement.setString(4, product.getDescription());
	        statement.setBigDecimal(5, product.getPrice());
	        statement.setString(6, product.getImageURL());
	        statement.setInt(7, product.getWarehouse_id());
	        statement.setString(8, product.getCategory());
	        statement.setString(9, product.getBarcode());
	        statement.executeUpdate();
	        statement.close();
	        connection.close();
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public Product addProductAndReturnProduct(Product product) {
		Product addedProduct = null;
		Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "INSERT INTO Products (Name, Stock, Weight, Description, Price, ImageURL, Warehouse_ID, Category, Barcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, product.getName());
	        statement.setInt(2, product.getStock());
	        statement.setBigDecimal(3, product.getWeight());
	        statement.setString(4, product.getDescription());
	        statement.setBigDecimal(5, product.getPrice());
	        statement.setString(6, product.getImageURL());
	        statement.setInt(7, product.getWarehouse_id());
	        statement.setString(8, product.getCategory());
	        statement.setString(9, product.getBarcode());
	        statement.executeUpdate();
	  
	        
	        String query2 = "SELECT * FROM Products WHERE Barcode = ?";
	        PreparedStatement statement2 = connection.prepareStatement(query2);
			statement2.setString(1, product.getBarcode());
			ResultSet resultSet = statement2.executeQuery();
			if (resultSet.next()) {
				addedProduct = new Product();
				addedProduct.setId(resultSet.getInt("Id"));
				addedProduct.setName(resultSet.getString("Name"));
				addedProduct.setStock(resultSet.getInt("Stock"));
				addedProduct.setWeight(resultSet.getBigDecimal("Weight"));
				addedProduct.setDescription(resultSet.getString("Description"));
				addedProduct.setPrice(resultSet.getBigDecimal("Price"));
				addedProduct.setImageURL(resultSet.getString("ImageURL"));
				addedProduct.setWarehouse_id(resultSet.getInt("Warehouse_ID"));
				addedProduct.setCategory(resultSet.getString("Category"));
				addedProduct.setBarcode(resultSet.getString("Barcode"));
			}
			
			statement.close();
	        connection.close();
	        
		}
		catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}	
		return addedProduct;
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
			String query = "UPDATE Products SET Name=?, Stock=?, Weight=?, Description=?, Price=?, ImageURL=?, Warehouse_ID = ?, Category = ?, Barcode = ? WHERE Id=?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, product.getName());
	        statement.setInt(2, product.getStock());
	        statement.setBigDecimal(3, product.getWeight());
	        statement.setString(4, product.getDescription());
	        statement.setBigDecimal(5, product.getPrice());
	        statement.setString(6, product.getImageURL());
	        statement.setInt(7, product.getWarehouse_id());
	        statement.setString(8, product.getCategory());
	        statement.setString(9, product.getBarcode());
	        statement.setInt(10,product.getId());
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
	
	public Product getProductByBarcode(String barcode) {
		Connection connection;
		Product product = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "SELECT * FROM Products WHERE Barcode = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, barcode);
			ResultSet resultSet = statement.executeQuery();
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
				product.setCategory(resultSet.getString("Category"));
				product.setBarcode(resultSet.getString("Barcode"));
			}
			statement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	public Product getProductById(int Id) {
		Connection connection;
		Product product = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "SELECT * FROM Products WHERE Id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, Id);
			ResultSet resultSet = statement.executeQuery();
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
				product.setCategory(resultSet.getString("Category"));
				product.setBarcode(resultSet.getString("Barcode"));
			}
			statement.close();
			connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return product;
	}

	public List<Product> searchProductsByName(String searchTerm, String sortBy) {
	    List<Product> products = new LinkedList<>();
	    Connection connection;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
	        String query = "SELECT * FROM Products WHERE Name LIKE ? ORDER BY " + sortBy;
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, "%" + searchTerm + "%");
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
	            product.setCategory(resultSet.getString("Category"));
	            product.setBarcode(resultSet.getString("Barcode"));
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
	
	public List<Product> searchProductsByNameAndCategory(String searchTerm, String category, String sortBy) {
	    List<Product> products = new LinkedList<>();
	    Connection connection;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
	        String query = "SELECT * FROM Products WHERE Name LIKE ? AND Category = ? ORDER BY " + sortBy;
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, "%" + searchTerm + "%");
	        statement.setString(2, category);
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
	            product.setCategory(resultSet.getString("Category"));
	            product.setBarcode(resultSet.getString("Barcode"));
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

	public List<CartItem> updateProductStockAfterOrder(List<CartItem> cartItemList) {
	    Connection connection;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
	        connection.setAutoCommit(false);

	        // Update stock for all products in the cartItemList
	        for (CartItem cartItem : cartItemList) {
	        	String query = "SELECT Stock FROM Products WHERE Barcode = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, cartItem.getProduct().getBarcode());
				ResultSet resultSet = statement.executeQuery();
				if (resultSet.next()) 
				{
					int stock = resultSet.getInt("Stock");
					cartItem.getProduct().setStock(stock);
				}
				statement.close();
	        }
	        
	        for (CartItem cartItem : cartItemList) {
	            Product product = cartItem.getProduct();
	            int oldStock = product.getStock();
	            int newStock = oldStock - cartItem.getQuantity();
	            
	            if( newStock < 0)
	            {
	            	connection.close();
	            	return cartItemList;
	            }
	            
	            String query = "UPDATE Products SET Stock=? WHERE Id=?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setInt(1, newStock);
	            statement.setInt(2, product.getId());
	            statement.executeUpdate();
	            statement.close();
	        }
	        connection.commit();
	        connection.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


}