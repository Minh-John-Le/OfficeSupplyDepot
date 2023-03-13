package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Beans.Store;

import java.sql.*;
import java.util.*;

public class StoreDAO {
    
    private Connection conn;
    
    public StoreDAO(Connection conn) {
        this.conn = conn;
    }
    
    public List<Store> getAllStores() throws SQLException {
        List<Store> stores = new ArrayList<>();
        String query = "SELECT * FROM stores";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String storeName = rs.getString("store_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Store store = new Store(id, storeName, username, password, email);
                stores.add(store);
            }
        }
        return stores;
    }
    
    public Store getStoreById(int id) throws SQLException {
        String query = "SELECT * FROM stores WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storeName = rs.getString("store_name");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    return new Store(id, storeName, username, password, email);
                }
            }
        }
        return null;
    }
    
    public void addStore(Store store) throws SQLException {
        String query = "INSERT INTO stores(store_name, username, password, email) VALUES(?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, store.getStoreName());
            pstmt.setString(2, store.getUsername());
            pstmt.setString(3, store.getPassword());
            pstmt.setString(4, store.getEmail());
            pstmt.executeUpdate();
        }
    }
    
    public void updateStore(Store store) throws SQLException {
        String query = "UPDATE stores SET store_name=?, username=?, password=?, email=? WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, store.getStoreName());
            pstmt.setString(2, store.getUsername());
            pstmt.setString(3, store.getPassword());
            pstmt.setString(4, store.getEmail());
            pstmt.setInt(5, store.getId());
            pstmt.executeUpdate();
        }
    }
    
    public void deleteStore(int id) throws SQLException {
        String query = "DELETE FROM stores WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    public void closeConnection() throws SQLException {
        conn.close();
    }
}
