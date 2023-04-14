package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.PaymentAccount;

public class PaymentAccountDAO {
    
    private String url ="";
    private String mySQLUser = "";
    private String mySQLPass = "";
    
    public PaymentAccountDAO(String url, String user, String password) 
    {
        this.url = url;
        this.mySQLUser = user;
        this.mySQLPass = password;
    }
    
    public void addPaymentAccount(PaymentAccount account)  {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "INSERT INTO PaymentAccounts (Customer_ID, Name, Expire_Date, Card_Number) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, account.getCustomerId());
            statement.setString(2, account.getName());
            statement.setString(3, account.getExpireDate());
            statement.setString(4, account.getCardNumber());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public PaymentAccount searchById(int id) {
        Connection connection;
        PaymentAccount account = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM PaymentAccounts WHERE Id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String name = rs.getString("Name");
                String expireDate = rs.getString("Expire_Date");
                String cardNumber = rs.getString("Card_Number");
                account = new PaymentAccount(id, customerId, name, expireDate, cardNumber);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return account;
    }
    
    public PaymentAccount searchByCustomerId(int customerId) {
        Connection connection;
        PaymentAccount account = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM PaymentAccounts WHERE Customer_ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String expireDate = rs.getString("Expire_Date");
                String cardNumber = rs.getString("Card_Number");
                account = new PaymentAccount(id, customerId, name, expireDate, cardNumber);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return account;
    }

    public PaymentAccount searchByAccountNumber(String accountNumber) {
        Connection connection;
        PaymentAccount account = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM PaymentAccounts WHERE Card_Number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, accountNumber);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("Id");
                int customerId = rs.getInt("Customer_ID");
                String name = rs.getString("Name");
                String expireDate = rs.getString("Expire_Date");
                account = new PaymentAccount(id, customerId, name, expireDate, accountNumber);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return account;
    }
    
    public void updatePaymentAccount(PaymentAccount account) {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "UPDATE PaymentAccounts SET Customer_ID = ?, Name = ?, Expire_Date = ?, Card_Number = ? WHERE Id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, account.getCustomerId());
            statement.setString(2, account.getName());
            statement.setString(3, account.getExpireDate());
            statement.setString(4, account.getCardNumber());
            statement.setInt(5, account.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    public void deleteAccount(int id) {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "DELETE FROM PaymentAccounts WHERE Id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
