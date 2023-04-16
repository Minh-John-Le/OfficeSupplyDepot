package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import beans.CartItem;
import beans.OrderPackage;

public class OrderPackageDAO {

    private String url;
    private String mySQLUser;
    private String mySQLPass;

    public OrderPackageDAO(String url, String user, String password) {
        this.url = url;
        this.mySQLUser = user;
        this.mySQLPass = password;
    }

    public void addOrderPackage(OrderPackage pkg) {
        try {
            Connection connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "INSERT INTO OrderPackages (Order_ID, Product_ID, Quantity) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pkg.getOrderID());
            statement.setInt(2, pkg.getProductID());
            statement.setInt(3, pkg.getQuantity());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPackage(List<CartItem> cartItemList, int orderId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Get connection
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);

            // Prepare statement
            String query = "INSERT INTO OrderPackages (Order_ID, Product_ID, Quantity) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(query);

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
            statement.close();
            connection.close();

        } catch (SQLException e) {
        	e.printStackTrace();
        } 
    }

    
    public List<OrderPackage> getPackagesByOrderId(int orderId) {
        List<OrderPackage> packages = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM OrderPackages WHERE Order_ID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderPackage pkg = new OrderPackage();
                pkg.setOrderID(resultSet.getInt("Order_ID"));
                pkg.setProductID(resultSet.getInt("Product_ID"));
                pkg.setQuantity(resultSet.getInt("Quantity"));
                packages.add(pkg);
            }
            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }
}
