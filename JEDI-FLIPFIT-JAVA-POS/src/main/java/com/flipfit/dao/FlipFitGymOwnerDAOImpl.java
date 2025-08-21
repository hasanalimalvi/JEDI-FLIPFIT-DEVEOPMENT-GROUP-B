package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.utils.DBConnection;

import java.sql.*;
import java.util.List;

public class FlipFitGymOwnerDAOImpl implements FlipFitGymOwnerDAO{


    @Override
    public FlipFitGymOwner registerGymOwner(FlipFitGymOwner gymOwner) {
        String insertUserSQL = "INSERT INTO FlipFitUser (username, email, password, roleId) VALUES (?, ?, ?, ?)";
        String insertGymOwnerSQL = "INSERT INTO FlipFitGymOwner (gymOwnerId, phoneNumber, city, pinCode, panCard, gstin, aadharNumber, isApproved) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            // Step 1: Insert into FlipFitUser
            try (PreparedStatement userStmt = conn.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS)) {
                userStmt.setString(1, gymOwner.getUsername());
                userStmt.setString(2, gymOwner.getEmail());
                userStmt.setString(3, gymOwner.getPassword());
                userStmt.setInt(4, gymOwner.getRoleId()); // e.g. 2 for gym owner

                userStmt.executeUpdate();

                ResultSet rs = userStmt.getGeneratedKeys();
                if (rs.next()) {
                    int userId = rs.getInt(1);
                    gymOwner.setUserId(userId); // Set FK for gym owner
                } else {
                    conn.rollback();
                    throw new SQLException("Failed to retrieve generated userId.");
                }
            }

            // Step 2: Insert into FlipFitGymOwner
            try (PreparedStatement gymStmt = conn.prepareStatement(insertGymOwnerSQL)) {
                gymStmt.setInt(1, gymOwner.getUserId());
                gymStmt.setString(2, gymOwner.getPhoneNumber());
                gymStmt.setString(3, gymOwner.getCity());
                gymStmt.setString(4, gymOwner.getPinCode());
                gymStmt.setString(5, gymOwner.getPanCard());
                gymStmt.setString(6, gymOwner.getGstin());
                gymStmt.setString(7, gymOwner.getAadharNumber());
                gymStmt.setBoolean(8, gymOwner.getIsApproved());

                gymStmt.executeUpdate();
            }

            conn.commit(); // Commit transaction
            System.out.println("Gym owner registered successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
            // Optional rollback logic
        }
        return gymOwner;
    }

    @Override
    public FlipFitGym addGym(FlipFitGym gym) {
        return null;
    }

    @Override
    public FlipFitGym updateGym(FlipFitGym gym) {
        return null;
    }

    @Override
    public FlipFitGym viewGym(int gymId) {
        return null;
    }

    @Override
    public List<FlipFitGym> viewGyms(int gymOwnerId) {
        return List.of();
    }

    @Override
    public List<FlipFitTransaction> viewTransactions(int gymId) {
        return List.of();
    }

    @Override
    public FlipFitGymOwner editDetails(FlipFitGymOwner gymOwner) {
        return null;
    }

    @Override
    public FlipFitGymOwner viewDetails(int gymOwnerId) {
        return null;
    }

    @Override
    public boolean deleteGym(int gymId) {
        return false;
    }

    @Override
    public List<FlipFitSlot> viewSlots(int gymId) {
        return List.of();
    }

    @Override
    public FlipFitGymOwner login(String gymOwnerName, String password) {
        return null;
    }

    @Override
    public FlipFitSlot addSlot(FlipFitSlot slot) {
        return null;
    }

    @Override
    public boolean deleteSlot(int slotId) {
        return false;
    }

    @Override
    public List<FlipFitBooking> viewBookings(int gymId) {
        return List.of();
    }
}
