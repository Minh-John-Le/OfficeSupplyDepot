package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.BankAccount;
import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;

public class BankAccountDAO {

	private BasicDataSource dataSource;
	private String url;
	private String mySQLUser;
	private String mySQLPass;

	public BankAccountDAO(String url, String user, String password) {
		this.url = url;
		this.mySQLUser = user;
		this.mySQLPass = password;
	}

	private DataSource getDataSource() {
		if (dataSource == null) {
			dataSource = new BasicDataSource();
			dataSource.setUrl(url);
			dataSource.setUsername(mySQLUser);
			dataSource.setPassword(mySQLPass);
			dataSource.setMinIdle(1);
			dataSource.setMaxIdle(1);
			dataSource.setMaxTotal(1);
		}
		return dataSource;
	}

	public void addBankAccount(BankAccount account) {
		String query = "INSERT INTO BankAccounts (Store_ID, Name, Expire_Date, Bank_Account_Number) VALUES (?, ?, ?, ?)";
		try (Connection connection = getDataSource().getConnection();
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
				String bankAccountNumber = rs.getString("Bank_Account_Number");
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

	public BankAccount searchByBankAccountNumber(String bankAccountNumber) {
		Connection connection;
		BankAccount account = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "SELECT * FROM BankAccounts WHERE Bank_Account_Number = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, bankAccountNumber);
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
