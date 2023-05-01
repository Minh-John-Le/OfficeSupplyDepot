package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Beans.BankAccount;

public class BankAccountDAO extends BaseDAO {

	public BankAccountDAO(String url, String user, String password) {
		super(url,user,password);
	}

	public void addBankAccount(BankAccount account) {
		String query = "INSERT INTO BankAccounts (Store_ID, Name, Expire_Date, Bank_Account_Number) VALUES (?, ?, ?, ?)";
		try (Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setInt(1, account.getStoreId());
			statement.setString(2, account.getName());
			statement.setString(3, account.getExpireDate());
			statement.setString(4, account.getBankAccountNumber());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateBankAccount(BankAccount account) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE BankAccounts SET Store_ID = ?, Name = ?, Expire_Date = ?, Bank_Account_Number = ? WHERE Id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, account.getStoreId());
                statement.setString(2, account.getName());
                statement.setString(3, account.getExpireDate());
                statement.setString(4, account.getBankAccountNumber());
                statement.setInt(5, account.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	public BankAccount searchById(int id) {
	    try (Connection connection = dataSource.getConnection()) {
	        String query = "SELECT * FROM BankAccounts WHERE Id = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, id);
	            try (ResultSet rs = statement.executeQuery()) {
	                if (rs.next()) {
	                    int storeId = rs.getInt("Store_ID");
	                    String name = rs.getString("Name");
	                    String expireDate = rs.getString("Expire_Date");
	                    String bankAccountNumber = rs.getString("Bank_Account_Number");
	                    return new BankAccount(id, storeId, name, expireDate, bankAccountNumber);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public BankAccount searchByStoreId(int storeId) {
	    try (Connection connection = dataSource.getConnection()) {
	        String query = "SELECT * FROM BankAccounts WHERE Store_ID = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, storeId);
	            try (ResultSet rs = statement.executeQuery()) {
	                if (rs.next()) {
	                    int id = rs.getInt("Id");
	                    String name = rs.getString("Name");
	                    String expireDate = rs.getString("Expire_Date");
	                    String bankAccountNumber = rs.getString("Bank_Account_Number");
	                    return new BankAccount(id, storeId, name, expireDate, bankAccountNumber);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public BankAccount searchByBankAccountNumber(String bankAccountNumber) {
	    BankAccount account = null;
	    String query = "SELECT * FROM BankAccounts WHERE Bank_Account_Number = ?";
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, bankAccountNumber);
	        try (ResultSet rs = statement.executeQuery()) {
	            if (rs.next()) {
	                int id = rs.getInt("Id");
	                int storeId = rs.getInt("Store_ID");
	                String name = rs.getString("Name");
	                String expireDate = rs.getString("Expire_Date");
	                account = new BankAccount(id, storeId, name, expireDate, bankAccountNumber);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return account;
	}
	
	public void deleteBankAccount(int id) {
	    try (Connection connection = dataSource.getConnection()) {
	        String query = "DELETE FROM BankAccounts WHERE Id = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, id);
	            statement.executeUpdate();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
