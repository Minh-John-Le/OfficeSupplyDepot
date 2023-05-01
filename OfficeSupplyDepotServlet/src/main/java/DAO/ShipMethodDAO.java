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
import Beans.ShipMethod;

public class ShipMethodDAO extends BaseDAO {

    public ShipMethodDAO(String url, String user, String password) 
    {
        super(url,user,password);
    }

    public List<ShipMethod> getAvailableShipMethod(String shipMethodListStr) {
        List<ShipMethod> availableShipMethods = new LinkedList<>();
        String query = "SELECT * FROM ShipMethods WHERE Id IN (" + shipMethodListStr +");";
        try (Connection connection = dataSource.getConnection();
        		PreparedStatement stmt = connection.prepareStatement(query)) {
            try(ResultSet rs = stmt.executeQuery()){
            	while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    BigDecimal price = rs.getBigDecimal("Price");
                    int speed = rs.getInt("Speed");
                    ShipMethod shipMethod = new ShipMethod();
                    shipMethod.setId(id);
                    shipMethod.setName(name);
                    shipMethod.setPrice(price);
                    shipMethod.setSpeed(speed);
                    availableShipMethods.add(shipMethod);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableShipMethods;
    }
    

	public ShipMethod getShipMethodById(int id) {
	    ShipMethod shipMethod = null;
	    String query = "SELECT * FROM ShipMethods WHERE Id = ?";
	    try (Connection connection = dataSource.getConnection();
        		PreparedStatement stmt = connection.prepareStatement(query)) {
	        stmt.setInt(1, id);
	        try(ResultSet rs = stmt.executeQuery()){
	        	if (rs.next()) {
		            String name = rs.getString("Name");
		            BigDecimal price = rs.getBigDecimal("Price");
		            int speed = rs.getInt("Speed");
		            shipMethod = new ShipMethod();
		            shipMethod.setId(id);
		            shipMethod.setName(name);
		            shipMethod.setPrice(price);
		            shipMethod.setSpeed(speed);
		        }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return shipMethod;
	}

}
