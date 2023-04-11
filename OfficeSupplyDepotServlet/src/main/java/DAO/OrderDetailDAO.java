package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Beans.BankAccount;
import Beans.OrderDetail;
import Beans.ShipMethod;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailDAO {
    
    private String url ="";
    private String mySQLUser = "";
    private String mySQLPass = "";
    
    public OrderDetailDAO(String url, String user, String password) 
    {
        this.url = url;
        this.mySQLUser = user;
        this.mySQLPass = password;
    }
    
    public void addOrderDetail(OrderDetail orderDetail) {
        try {
            Connection connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "INSERT INTO OrderDetails (Customer_ID, Order_Code, Shipmethod_ID, Ship_Address, Total_Weight, Total_Price, Payment_Card_Number, Card_Name, Expire_Date, Delivery_Name, Order_Date, Delivery_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderDetail.getCustomerID());
            statement.setString(2, orderDetail.getOrderCode());
            statement.setInt(3, orderDetail.getShipmethodID());
            statement.setString(4, orderDetail.getShipAddress());
            statement.setBigDecimal(5, orderDetail.getTotalWeight());
            statement.setBigDecimal(6, orderDetail.getTotalPrice());
            statement.setInt(7, orderDetail.getPaymentCardNumber());
            statement.setString(8, orderDetail.getCardName());
            statement.setString(9, orderDetail.getExpireDate());
            statement.setString(10, orderDetail.getDeliveryName());
            statement.setString(11, orderDetail.getOrderDate());
            statement.setString(12, orderDetail.getDeliveryDate());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<OrderDetail> getOrderDetailByCustomerId(int customerId) {
        List<OrderDetail> orderDetails = new LinkedList<>();
        try {
            Connection connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM OrderDetails WHERE Customer_ID=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(resultSet.getInt("Id"));
                orderDetail.setCustomerID(resultSet.getInt("Customer_ID"));
                orderDetail.setOrderCode(resultSet.getString("Order_Code"));
                orderDetail.setShipmethodID(resultSet.getInt("Shipmethod_ID"));
                orderDetail.setShipAddress(resultSet.getString("Ship_Address"));
                orderDetail.setTotalWeight(resultSet.getBigDecimal("Total_Weight"));
                orderDetail.setTotalPrice(resultSet.getBigDecimal("Total_Price"));
                orderDetail.setPaymentCardNumber(resultSet.getInt("Payment_Card_Number"));
                orderDetail.setCardName(resultSet.getString("Card_Name"));
                orderDetail.setExpireDate(resultSet.getString("Expire_Date"));
                orderDetail.setDeliveryName(resultSet.getString("Delivery_Name"));
                orderDetail.setOrderDate(resultSet.getString("Order_Date"));
                orderDetail.setDeliveryDate(resultSet.getString("Delivery_Date"));
                orderDetails.add(orderDetail);
            }
            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
    
    public OrderDetail getOrderDetailById(int orderId) {
        OrderDetail orderDetail = null;
        try {
            Connection connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM OrderDetails WHERE Id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orderDetail = new OrderDetail();
                orderDetail.setId(resultSet.getInt("Id"));
                orderDetail.setCustomerID(resultSet.getInt("Customer_ID"));
                orderDetail.setOrderCode(resultSet.getString("Order_Code"));
                orderDetail.setShipmethodID(resultSet.getInt("Shipmethod_ID"));
                orderDetail.setShipAddress(resultSet.getString("Ship_Address"));
                orderDetail.setTotalWeight(resultSet.getBigDecimal("Total_Weight"));
                orderDetail.setTotalPrice(resultSet.getBigDecimal("Total_Price"));
                orderDetail.setPaymentCardNumber(resultSet.getInt("Payment_Card_Number"));
                orderDetail.setCardName(resultSet.getString("Card_Name"));
                orderDetail.setExpireDate(resultSet.getString("Expire_Date"));
                orderDetail.setDeliveryName(resultSet.getString("Delivery_Name"));
                orderDetail.setOrderDate(resultSet.getString("Order_Date"));
                orderDetail.setDeliveryDate(resultSet.getString("Delivery_Date"));
            }
            statement.close();
            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }
}

