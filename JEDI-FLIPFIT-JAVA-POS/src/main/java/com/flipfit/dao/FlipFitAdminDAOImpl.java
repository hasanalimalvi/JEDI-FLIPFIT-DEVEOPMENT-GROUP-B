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
        List<FlipFitGym> pendingGyms = new ArrayList<>();
        String query = "SELECT * FROM FlipFitGym WHERE isApproved = false";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlipFitGym gym = new FlipFitGym();
                gym.setGymID(rs.getInt("gymID"));
                gym.setGymOwnerID(rs.getInt("gymOwnerID"));
                gym.setAddress(rs.getString("address"));
                gym.setPinCode(rs.getString("pinCode"));
                gym.setApproved(rs.getBoolean("isApproved"));
                gym.setDescription(rs.getString("description"));

                pendingGyms.add(gym);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pendingGyms;
    }

    @Override
    public List<FlipFitGym> getApprovedGymList() {
        List<FlipFitGym> approvedGyms = new ArrayList<>();
        String query = "SELECT * FROM FlipFitGym WHERE isApproved = true";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlipFitGym gym = new FlipFitGym();
                gym.setGymID(rs.getInt("gymID"));
                gym.setGymOwnerID(rs.getInt("gymOwnerID"));
                gym.setAddress(rs.getString("address"));
                gym.setPinCode(rs.getString("pinCode"));
                gym.setApproved(rs.getBoolean("isApproved"));
                gym.setDescription(rs.getString("description"));

                approvedGyms.add(gym);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return approvedGyms;
    }

    @Override
    public List<FlipFitDirectCustomer> getCustomerList() {
        List<FlipFitDirectCustomer> customerList = new ArrayList<>();

        String sql = """
        SELECT 
            u.userId, u.username, u.email, u.password, u.roleId,
            dc.phoneNumber, dc.city, dc.pinCode
        FROM FlipFitUser u
        JOIN FlipFitDirectCustomer dc ON u.userId = dc.customerId
    """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlipFitDirectCustomer customer = new FlipFitDirectCustomer();

                // Fields from FlipFitUser
                customer.setUserId(rs.getInt("userId"));
                customer.setUsername(rs.getString("username"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setRoleId(rs.getInt("roleId"));

                // Fields from FlipFitDirectCustomer
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setCity(rs.getString("city"));
                customer.setPinCode(rs.getString("pinCode"));

                customerList.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving customer list", e);
        }

        return customerList;
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
        List<FlipFitGym> gymList = new ArrayList<>();
        String sql = "SELECT * FROM FlipFitGym";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FlipFitGym gym = new FlipFitGym();
                gym.setGymID(rs.getInt("gymId"));
                gym.setGymOwnerID(rs.getInt("gymOwnerID"));
                gym.setAddress(rs.getString("address"));
                gym.setPinCode(rs.getString("pinCode"));
                gym.setApproved(rs.getBoolean("isApproved"));
                gym.setDescription(rs.getString("description"));

                gymList.add(gym);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving gym list: " + e.getMessage());
        }
        return gymList;
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
