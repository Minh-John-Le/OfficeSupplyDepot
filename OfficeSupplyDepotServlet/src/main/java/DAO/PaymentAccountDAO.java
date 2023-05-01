package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.PaymentAccount;

public class PaymentAccountDAO extends BaseDAO{
	
    public PaymentAccountDAO(String url, String user, String password) 
    {
        super(url,user,password);
    }
    
    public void addPaymentAccount(PaymentAccount account)  {
        String query = "INSERT INTO PaymentAccounts (Customer_ID, Name, Expire_Date, Card_Number) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setInt(1, account.getCustomerId());
            statement.setString(2, account.getName());
            statement.setString(3, account.getExpireDate());
            statement.setString(4, account.getCardNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public PaymentAccount searchById(int id) {
        String query = "SELECT * FROM PaymentAccounts WHERE Id = ?";
        PaymentAccount account = null;
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
        
            statement.setInt(1, id);
            try(ResultSet rs = statement.executeQuery()){
            	 if (rs.next()) {
                     int customerId = rs.getInt("Customer_ID");
                     String name = rs.getString("Name");
                     String expireDate = rs.getString("Expire_Date");
                     String cardNumber = rs.getString("Card_Number");
                     account = new PaymentAccount(id, customerId, name, expireDate, cardNumber);
                 }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
    
    public PaymentAccount searchByCustomerId(int customerId) {
    	String query = "SELECT * FROM PaymentAccounts WHERE Customer_ID = ?";
        PaymentAccount account = null;
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            try(ResultSet rs = statement.executeQuery()){
	            if (rs.next()) {
	                int id = rs.getInt("Id");
	                String name = rs.getString("Name");
	                String expireDate = rs.getString("Expire_Date");
	                String cardNumber = rs.getString("Card_Number");
	                account = new PaymentAccount(id, customerId, name, expireDate, cardNumber);
	            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public PaymentAccount searchByAccountNumber(String accountNumber) {
    	String query = "SELECT * FROM PaymentAccounts WHERE Card_Number = ?";
        PaymentAccount account = null;
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, accountNumber);
            try(ResultSet rs = statement.executeQuery()){
            	if (rs.next()) {
                    int id = rs.getInt("Id");
                    int customerId = rs.getInt("Customer_ID");
                    String name = rs.getString("Name");
                    String expireDate = rs.getString("Expire_Date");
                    account = new PaymentAccount(id, customerId, name, expireDate, accountNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }
    
    public void updatePaymentAccount(PaymentAccount account) {
    	String query = "UPDATE PaymentAccounts SET Customer_ID = ?, Name = ?, Expire_Date = ?, Card_Number = ? WHERE Id = ?";
    	try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, account.getCustomerId());
            statement.setString(2, account.getName());
            statement.setString(3, account.getExpireDate());
            statement.setString(4, account.getCardNumber());
            statement.setInt(5, account.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void deleteAccount(int id) {
    	String query = "DELETE FROM PaymentAccounts WHERE Id = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query)) {
            	statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
