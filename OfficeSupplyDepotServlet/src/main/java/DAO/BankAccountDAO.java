package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.BankAccount;

public class BankAccountDAO {

    private String url ="";
    private String mySQLUser = "";
    private String mySQLPass = "";

    public BankAccountDAO(String url, String user, String password) 
    {
        this.url = url;
        this.mySQLUser = user;
        this.mySQLPass = password;
    }

    public void addBankAccount(BankAccount account)  {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "INSERT INTO BankAccounts (Store_ID, Name, Expire_Date, Bank_Account_Number) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, account.getStoreId());
            statement.setString(2, account.getName());
            statement.setString(3, account.getExpireDate());
            statement.setInt(4, account.getBankAccountNumber());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void updateBankAccount(BankAccount account) {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "UPDATE BankAccounts SET Store_ID = ?, Name = ?, Expire_Date = ?, Bank_Account_Number = ? WHERE Id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, account.getStoreId());
            statement.setString(2, account.getName());
            statement.setString(3, account.getExpireDate());
            statement.setInt(4, account.getBankAccountNumber());
            statement.setInt(5, account.getId());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BankAccount searchById(int id) {
        Connection connection;
        BankAccount account = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM BankAccounts WHERE Id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int storeId = rs.getInt("Store_ID");
                String name = rs.getString("Name");
                String expireDate = rs.getString("Expire_Date");
                int bankAccountNumber = rs.getInt("Bank_Account_Number");
                account = new BankAccount(id, storeId, name, expireDate, bankAccountNumber);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return account;
    }
    
    public BankAccount searchByStoreId(int storeId) {
        Connection connection;
        BankAccount account = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM BankAccounts WHERE Store_ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, storeId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String expireDate = rs.getString("Expire_Date");
                int bankAccountNumber = rs.getInt("Bank_Account_Number");
                account = new BankAccount(id, storeId, name, expireDate, bankAccountNumber);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return account;
    }



    public BankAccount searchByBankAccountNumber(int bankAccountNumber) {
        Connection connection;
        BankAccount account = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM BankAccounts WHERE Bank_Account_Number = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bankAccountNumber);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("Id");
                int storeId = rs.getInt("Store_ID");
                String name = rs.getString("Name");
                String expireDate = rs.getString("Expire_Date");
                account = new BankAccount(id, storeId, name, expireDate, bankAccountNumber);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return account;
    }

    public void deleteBankAccount(int id) {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "DELETE FROM BankAccounts WHERE Id = ?";
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
