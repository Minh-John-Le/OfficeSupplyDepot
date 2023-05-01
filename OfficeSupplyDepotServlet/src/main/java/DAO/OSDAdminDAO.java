package DAO;

import Beans.OSDAdmin;
import java.sql.*;
import java.util.*;

public class OSDAdminDAO extends BaseDAO {
    
    public OSDAdminDAO(String url, String user, String password) 
    {
    	super(url,user,password);
    }
    
    public void addAdmin(OSDAdmin admin) {
        String query = "INSERT INTO OSDAdmins (AdminName, Username, Password, Email) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getAdminName());
            statement.setString(2, admin.getUsername());
            statement.setString(3, admin.getPassword());
            statement.setString(4, admin.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    public void deleteAdmin(int adminID) {
        String query = "DELETE FROM OSDAdmins WHERE Id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public OSDAdmin getAdminById(int adminId) {
        String query = "SELECT * FROM OSDAdmins WHERE Id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                OSDAdmin admin = null;
                if (resultSet.next()) {
                    admin = new OSDAdmin();
                    admin.setId(resultSet.getInt("Id"));
                    admin.setAdminName(resultSet.getString("AdminName"));
                    admin.setUsername(resultSet.getString("Username"));
                    admin.setPassword(resultSet.getString("Password"));
                    admin.setEmail(resultSet.getString("Email"));
                }
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    
    public OSDAdmin getAdminByUsername(String username) {
        String query = "SELECT * FROM OSDAdmins WHERE Username = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
	            OSDAdmin admin = null;
	            if (resultSet.next()) {
	            	admin = new OSDAdmin();
	            	admin.setId(resultSet.getInt("Id"));
	            	admin.setAdminName(resultSet.getString("AdminName"));
	            	admin.setUsername(resultSet.getString("Username"));
	            	admin.setPassword(resultSet.getString("Password"));
	            	admin.setEmail(resultSet.getString("Email"));
	            }
	            return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAdmin(OSDAdmin admin) {
    	String query = "UPDATE OSDAdmins SET AdminName=?, Username=?, Password=?, Email=? WHERE Id=?";
    	try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, admin.getAdminName());
	        statement.setString(2, admin.getUsername());
	        statement.setString(3, admin.getPassword());
	        statement.setString(4, admin.getEmail());
	        statement.setInt(5, admin.getId());
	        statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public List<OSDAdmin> getAllAdmins() {
    	String query = "SELECT * FROM OSDAdmins";
    	try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
	        try(ResultSet resultSet = statement.executeQuery()){
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
		        return admins;
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }
}
