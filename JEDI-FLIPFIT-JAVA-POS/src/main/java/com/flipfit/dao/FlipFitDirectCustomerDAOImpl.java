package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.utils.DBConnection;

import java.sql.*;
import java.util.List;

public class FlipFitDirectCustomerDAOImpl implements FlipFitDirectCustomerDAO{
    @Override
    public List<FlipFitSlot> viewSlots(int gymId) {
        return List.of();
    }

    @Override
    public List<FlipFitBooking> viewBookedSlots(int userId) {
        return List.of();
    }

    @Override
    public FlipFitDirectCustomer viewDetails(int customerId) {
        return null;
    }

    @Override
    public FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) {
        String insertUserSQL = "INSERT INTO FlipFitUser (username, email, password, roleId) VALUES (?, ?, ?, ?)";
        String insertCustomerSQL = "INSERT INTO FlipFitDirectCustomer (customerId, phoneNumber, city, pinCode) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement userStmt = conn.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement customerStmt = conn.prepareStatement(insertCustomerSQL)) {

            // Insert into FlipFitUser
            userStmt.setString(1, directCustomer.getUsername());
            userStmt.setString(2, directCustomer.getEmail());
            userStmt.setString(3, directCustomer.getPassword());
            userStmt.setInt(4, directCustomer.getRoleId());
            userStmt.executeUpdate();

            // Get generated userId
            ResultSet rs = userStmt.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1);
                directCustomer.setUserId(userId);

                // Insert into FlipFitDirectCustomer
                customerStmt.setInt(1, userId);
                customerStmt.setString(2, directCustomer.getPhoneNumber());
                customerStmt.setString(3, directCustomer.getCity());
                customerStmt.setString(4, directCustomer.getPinCode());
                customerStmt.executeUpdate();

                return directCustomer;
            } else {
                throw new SQLException("User ID generation failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer) {
        return null;
    }

    @Override
    public List<FlipFitGym> viewGyms() {
        return List.of();
    }

    @Override
    public FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction) {
        return null;
    }

    @Override
    public FlipFitDirectCustomer login(String customerName, String password) {
        return null;
    }

    @Override
    public FlipFitBooking makeFlipFitBooking(int customerID, int slotId) {
        return null;
    }

    @Override
    public boolean cancelFlipFitBooking(int bookingId) {
        return false;
    }
}
