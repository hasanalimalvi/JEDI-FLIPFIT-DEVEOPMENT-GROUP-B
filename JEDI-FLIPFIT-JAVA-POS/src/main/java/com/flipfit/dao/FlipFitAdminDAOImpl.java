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
        return List.of();
    }

    @Override
    public List<FlipFitGymOwner> getApprovedGymOwnerList() {
        return List.of();
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
        List<FlipFitGymOwner> gymOwners = new ArrayList<>();
        // SQL query to join FlipFitUser and FlipFitGymOwner tables
        String sql = "SELECT u.userId, u.username, u.email, u.password, u.roleId, " +
                "go.phoneNumber, go.city, go.pinCode, go.panCard, go.gstin, go.aadharNumber, go.isApproved " +
                "FROM FlipFitUser u " +
                "JOIN FlipFitGymOwner go ON u.userId = go.gymOwnerId " +
                "WHERE u.roleId = (SELECT roleId FROM FlipFitRole WHERE roleName = 'GymOwner')"; // Filters by GymOwner role

        try (Connection conn = DBConnection.getConnection(); // Get connection from your DBConnection utility
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // Execute query directly since no parameters

            while (rs.next()) {
                // Extract data from ResultSet for FlipFitUser fields
                int userId = rs.getInt("userId");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int roleId = rs.getInt("roleId");

                // Extract data from ResultSet for FlipFitGymOwner specific fields
                String phoneNumber = rs.getString("phoneNumber");
                String city = rs.getString("city");
                String pinCode = rs.getString("pinCode");
                String panCard = rs.getString("panCard");
                String gstin = rs.getString("gstin");
                String aadharNumber = rs.getString("aadharNumber");
                boolean isApproved = rs.getBoolean("isApproved");

                // Create and populate FlipFitGymOwner object using setters
                FlipFitGymOwner gymOwner = new FlipFitGymOwner();
                gymOwner.setUserId(userId);
                gymOwner.setUsername(username);
                gymOwner.setEmail(email);
                gymOwner.setPassword(password);
                gymOwner.setRoleId(roleId);
                gymOwner.setPhoneNumber(phoneNumber);
                gymOwner.setCity(city);
                gymOwner.setPinCode(pinCode);
                gymOwner.setPanCard(panCard);
                gymOwner.setGstin(gstin);
                gymOwner.setAadharNumber(aadharNumber);
                gymOwner.setIsApproved(isApproved);

                gymOwners.add(gymOwner);
            }
            System.out.println("✅ Retrieved " + gymOwners.size() + " gym owners from the database.");

        } catch (SQLException e) {
            System.err.println("❌ Database error during getGymOwners: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list on error
        }
        return gymOwners;
    }
    @Override
    public List<FlipFitGym> getGyms() {
        return List.of();
    }

    @Override
    public boolean validateGymOwner(int gymOwnerId) {
        String updateSQL = "UPDATE FlipFitGymOwner SET isApproved = ? WHERE gymOwnerId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

            stmt.setBoolean(1, true);
            stmt.setInt(2, gymOwnerId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validateGym(int gymId) {
        String updateSQL = "UPDATE FlipFitGym SET status = ? WHERE gymId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

            stmt.setString(1, "approved");
            stmt.setInt(2, gymId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
