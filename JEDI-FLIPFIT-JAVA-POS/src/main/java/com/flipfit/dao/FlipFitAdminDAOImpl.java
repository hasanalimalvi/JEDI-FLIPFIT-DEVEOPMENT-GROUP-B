package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminDAOImpl implements FlipFitAdminDAO{
    @Override
    public List<FlipFitGymOwner> getPendingGymOwnerList() {
        List<FlipFitGymOwner> pendingOwners = new ArrayList<>();

        String query = """
        SELECT g.*, u.*
        FROM FlipFitGymOwner g
        JOIN FlipFitUser u ON g.gymOwnerId = u.userId
        WHERE g.isApproved = false
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlipFitGymOwner owner = new FlipFitGymOwner();
                owner.setUserId(rs.getInt("gymOwnerId"));
                owner.setPhoneNumber(rs.getString("phoneNumber"));
                owner.setCity(rs.getString("city"));
                owner.setPinCode(rs.getString("pinCode"));
                owner.setPanCard(rs.getString("panCard"));
                owner.setGstin(rs.getString("gstin"));
                owner.setAadharNumber(rs.getString("aadharNumber"));
                owner.setIsApproved(rs.getBoolean("isApproved"));

                // Set user details from FlipFitUser
                owner.setUsername(rs.getString("username"));
                owner.setEmail(rs.getString("email"));
                owner.setPassword(rs.getString("password"));
                owner.setRoleId(rs.getInt("roleId"));

                pendingOwners.add(owner);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pendingOwners;
    }

    @Override
    public List<FlipFitGymOwner> getApprovedGymOwnerList() {
        List<FlipFitGymOwner> approvedOwners = new ArrayList<>();

        String query = """
            SELECT g.*, u.*
            FROM FlipFitGymOwner g
            JOIN FlipFitUser u ON g.gymOwnerId = u.userId
            WHERE g.isApproved = true
        """;

            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    FlipFitGymOwner owner = new FlipFitGymOwner();
                    owner.setUserId(rs.getInt("gymOwnerId"));
                    owner.setPhoneNumber(rs.getString("phoneNumber"));
                    owner.setCity(rs.getString("city"));
                    owner.setPinCode(rs.getString("pinCode"));
                    owner.setPanCard(rs.getString("panCard"));
                    owner.setGstin(rs.getString("gstin"));
                    owner.setAadharNumber(rs.getString("aadharNumber"));
                    owner.setIsApproved(rs.getBoolean("isApproved"));

                    // Set user details from FlipFitUser
                    owner.setUsername(rs.getString("username"));
                    owner.setEmail(rs.getString("email"));
                    owner.setPassword(rs.getString("password"));
                    owner.setRoleId(rs.getInt("roleId"));

                    approvedOwners.add(owner);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return approvedOwners;
    }

    @Override
    public List<FlipFitGym> getPendingGymList() {
        return List.of();
    }

    @Override
    public List<FlipFitGym> getApprovedGymList() {
        return List.of();
    }

    @Override
    public List<FlipFitDirectCustomer> getCustomerList() {
        return List.of();
    }

    @Override
    public List<FlipFitGymOwner> getGymOwners() {
        return List.of();
    }

    @Override
    public List<FlipFitGym> getGyms() {
        return List.of();
    }

    @Override
    public boolean validateGymOwner(int gymOwnerId) {
        return false;
    }

    @Override
    public boolean validateGym(int gymId) {
        return false;
    }

    @Override
    public FlipFitAdmin login(String adminName, String password) {
        String userQuery = "SELECT * FROM FlipFitUser WHERE username = ? AND password = ?";
        String adminQuery = "SELECT * FROM FlipFitAdmin WHERE adminId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement userStmt = conn.prepareStatement(userQuery)) {

            // Step 1: Validate credentials from FlipFitUser
            userStmt.setString(1, adminName);
            userStmt.setString(2, password);

            ResultSet userRs = userStmt.executeQuery();
            if (userRs.next()) {
                int userId = userRs.getInt("userId");

                // Step 2: Fetch admin-specific details
                try (PreparedStatement adminStmt = conn.prepareStatement(adminQuery)) {
                    adminStmt.setInt(1, userId);
                    ResultSet adminRs = adminStmt.executeQuery();

                    if (adminRs.next()) {
                        FlipFitAdmin admin = new FlipFitAdmin();

                        // Populate FlipFitUser fields
                        admin.setUserId(userId);
                        admin.setUsername(userRs.getString("username"));
                        admin.setEmail(userRs.getString("email"));
                        admin.setPassword(userRs.getString("password"));
                        admin.setRoleId(userRs.getInt("roleId"));

                        return admin;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this properly
        }

        return null; // Login failed
    }


    @Override
    public List<FlipFitTransaction> viewPayments() {
        return List.of();
    }
}
