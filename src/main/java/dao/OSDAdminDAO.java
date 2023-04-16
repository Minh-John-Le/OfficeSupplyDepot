package dao;

import beans.Customer;
import beans.OSDAdmin;

import java.sql.*;
import java.util.*;

public class OSDAdminDAO {
    
    private String url ="";
    private String mySQLUser = "";
    private String mySQLPass = "";
    
    public OSDAdminDAO(String url, String user, String password) 
    {
    	this.url = url;
    	this.mySQLUser = user;
    	this.mySQLPass = password;
    }
    
    public void addAdmin(OSDAdmin admin)  {
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "INSERT INTO OSDAdmins (AdminName, Username, Password, Email) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, admin.getAdminName());
	        statement.setString(2, admin.getUsername());
	        statement.setString(3, admin.getPassword());
	        statement.setString(4, admin.getEmail());
	        statement.executeUpdate();
	        statement.close();
	        connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    public void deleteAdmin(int adminID) {
    	
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
		 	String query = "DELETE FROM OSDAdmins WHERE Id = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, adminID);
	        statement.executeUpdate();
	        statement.close();
	        connection.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    
    public OSDAdmin getAdminById(int adminId) {
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "SELECT * FROM OSDAdmins WHERE Id = ?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, adminId);
	        ResultSet resultSet = statement.executeQuery();
	        OSDAdmin admin = null;
	        if(resultSet.next()) {
	        	admin = new OSDAdmin();
	        	admin.setId(resultSet.getInt("Id"));
	        	admin.setAdminName(resultSet.getString("AdminName"));
	        	admin.setUsername(resultSet.getString("Username"));
	            admin.setPassword(resultSet.getString("Password"));
	            admin.setEmail(resultSet.getString("Email"));;
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        return admin;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
    
    public OSDAdmin getAdminByUsername(String username) {
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
            String query = "SELECT * FROM OSDAdmins WHERE Username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            OSDAdmin admin = null;
            if (resultSet.next()) {
            	admin = new OSDAdmin();
            	admin.setId(resultSet.getInt("Id"));
            	admin.setAdminName(resultSet.getString("AdminName"));
            	admin.setUsername(resultSet.getString("Username"));
            	admin.setPassword(resultSet.getString("Password"));
            	admin.setEmail(resultSet.getString("Email"));
            }
            resultSet.close();
            statement.close();
            connection.close();
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAdmin(OSDAdmin admin) {
        
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "UPDATE OSDAdmins SET AdminName=?, Username=?, Password=?, Email=? WHERE Id=?";
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, admin.getAdminName());
	        statement.setString(2, admin.getUsername());
	        statement.setString(3, admin.getPassword());
	        statement.setString(4, admin.getEmail());
	        statement.setInt(5, admin.getId());
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
    }
    
    public List<OSDAdmin> getAllAdmins() {
    	
    	Connection connection;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, mySQLUser, mySQLPass);
			String query = "SELECT * FROM OSDAdmins";
	        PreparedStatement statement = connection.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        List<OSDAdmin> admins = new ArrayList<>();
	        while(resultSet.next()) {
	            OSDAdmin admin = new OSDAdmin();
	            admin.setId(resultSet.getInt("id"));
	            admin.setAdminName(resultSet.getString("AdminName"));
	            admin.setUsername(resultSet.getString("Username"));
	            admin.setPassword(resultSet.getString("Password"));
	            admin.setEmail(resultSet.getString("Email"));
	            admins.add(admin);
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        return admins;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;     
    }
    
}
