package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Beans.CartItem;
import Beans.OrderPackage;

public class OrderPackageDAO extends BaseDAO {
    
	public OrderPackageDAO(String url, String user, String password) {
    	super(url,user,password);
    }

    public void addOrderPackage(OrderPackage pkg) {
    	try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO OrderPackages (Order_ID, Product_ID, Quantity) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
            	statement.setInt(1, pkg.getOrderID());
	            statement.setInt(2, pkg.getProductID());
	            statement.setInt(3, pkg.getQuantity());
	            statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPackage(List<CartItem> cartItemList, int orderId) {
        try (Connection connection = dataSource.getConnection()) {
            // Prepare statement
            String query = "INSERT INTO OrderPackages (Order_ID, Product_ID, Quantity) VALUES (?, ?, ?)";
            try(PreparedStatement statement = connection.prepareStatement(query)){
	
	            // Iterate through cart items and add to batch
	            for (CartItem cartItem : cartItemList) {
	                // Set parameters for each item in batch
	                statement.setInt(1, orderId);
	                statement.setInt(2, cartItem.getProduct().getId());
	                statement.setInt(3, cartItem.getQuantity());
	
	                // Add statement to batch
	                statement.addBatch();
	            }
	            // Execute batch
	            statement.executeBatch();
            }

        } catch (SQLException e) {
        	e.printStackTrace();
        } 
    }

    
    public List<OrderPackage> getPackagesByOrderId(int orderId) {
        List<OrderPackage> packages = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM OrderPackages WHERE Order_ID=?";
            try(PreparedStatement statement = connection.prepareStatement(query)){
	            statement.setInt(1, orderId);
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                OrderPackage pkg = new OrderPackage();
	                pkg.setOrderID(resultSet.getInt("Order_ID"));
	                pkg.setProductID(resultSet.getInt("Product_ID"));
	                pkg.setQuantity(resultSet.getInt("Quantity"));
	                packages.add(pkg);
	            }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }
}
