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

public class ShipMethodDAO {

    private String url ="";
    private String mySQLUser = "";
    private String mySQLPass = "";

    public ShipMethodDAO(String url, String user, String password) 
    {
        this.url = url;
        this.mySQLUser = user;
        this.mySQLPass = password;
    }

    public List<ShipMethod> getAvailableShipMethod(List<Integer> shipMethodList) {
        List<ShipMethod> availableShipMethods = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(url, mySQLUser, mySQLPass)) {
            String query = "SELECT * FROM ShipMethod WHERE Id IN (?) AND IsActive = 1";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setArray(1, conn.createArrayOf("INTEGER", shipMethodList.toArray()));
            ResultSet rs = stmt.executeQuery();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableShipMethods;
    }

}
