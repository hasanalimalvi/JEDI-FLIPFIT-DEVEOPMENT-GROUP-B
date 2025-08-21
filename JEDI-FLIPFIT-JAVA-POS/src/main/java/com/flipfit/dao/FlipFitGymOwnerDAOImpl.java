package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
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
        String insertGymSQL = "INSERT INTO FlipFitGym (gymOwnerID, address, pinCode, isApproved, description) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement gymStmt = conn.prepareStatement(insertGymSQL, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters
            gymStmt.setInt(1, gym.getGymOwnerID());
            gymStmt.setString(2, gym.getAddress());
            gymStmt.setString(3, gym.getPinCode());
            gymStmt.setBoolean(4, gym.isApproved());
            gymStmt.setString(5, gym.getDescription());

            // Execute insert
            gymStmt.executeUpdate();

            // Retrieve generated gymID
            ResultSet rs = gymStmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedGymID = rs.getInt(1);
                gym.setGymID(generatedGymID);
                return gym;
            } else {
                throw new SQLException("Failed to retrieve generated gymID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FlipFitGym updateGym(FlipFitGym gym) {
        String updateSQL = "UPDATE FlipFitGym SET address = ?, pinCode = ?, isApproved = ?, description = ? WHERE gymID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

            stmt.setString(1, gym.getAddress());
            stmt.setString(2, gym.getPinCode());
            stmt.setBoolean(3, gym.isApproved());
            stmt.setString(4, gym.getDescription());
            stmt.setInt(5, gym.getGymID());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                return gym; // Return the updated gym object
            } else {
                return null; // No rows updated, possibly invalid gymID
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FlipFitGym viewGym(int gymId) {
        String selectGymSQL = "SELECT gymID, gymOwnerID, address, pinCode, isApproved, description FROM FlipFitGym WHERE gymID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectGymSQL)) {

            stmt.setInt(1, gymId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                FlipFitGym gym = new FlipFitGym();
                gym.setGymID(rs.getInt("gymID"));
                gym.setGymOwnerID(rs.getInt("gymOwnerID"));
                gym.setAddress(rs.getString("address"));
                gym.setPinCode(rs.getString("pinCode"));
                gym.setApproved(rs.getBoolean("isApproved"));
                gym.setDescription(rs.getString("description"));
                return gym;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if gym not found or error occurs
    }

    @Override
    public List<FlipFitGym> viewGyms(int gymOwnerId) {
        List<FlipFitGym> gyms = new ArrayList<>();
        String query = "SELECT * FROM FlipFitGym WHERE gymOwnerID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, gymOwnerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FlipFitGym gym = new FlipFitGym();
                gym.setGymID(rs.getInt("gymID"));
                gym.setGymOwnerID(rs.getInt("gymOwnerID"));
                gym.setAddress(rs.getString("address"));
                gym.setPinCode(rs.getString("pinCode"));
                gym.setApproved(rs.getBoolean("isApproved"));
                gym.setDescription(rs.getString("description"));

                gyms.add(gym);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gyms;
    }

    @Override
    public List<FlipFitTransaction> viewTransactions(int gymId) {
        return List.of();
    }

    @Override
    public FlipFitGymOwner editDetails(FlipFitGymOwner gymOwner) {
        Connection conn = null;
        PreparedStatement userStmt = null;
        PreparedStatement ownerStmt = null;

        try {
            conn = DBConnection.getConnection();

            // Step 1: Update FlipFitUser table
            String updateUserQuery = "UPDATE FlipFitUser SET username = ?, email = ?, password = ?, roleId = ? WHERE userId = ?";
            userStmt = conn.prepareStatement(updateUserQuery);
            userStmt.setString(1, gymOwner.getUsername());
            userStmt.setString(2, gymOwner.getEmail());
            userStmt.setString(3, gymOwner.getPassword());
            userStmt.setInt(4, gymOwner.getRoleId());
            userStmt.setInt(5, gymOwner.getUserId());
            int userRows = userStmt.executeUpdate();

            // Step 2: Update FlipFitGymOwner table
            String updateOwnerQuery = "UPDATE FlipFitGymOwner SET phoneNumber = ?, city = ?, pinCode = ?, panCard = ?, gstin = ?, aadharNumber = ?, isApproved = ? WHERE gymOwnerId = ?";
            ownerStmt = conn.prepareStatement(updateOwnerQuery);
            ownerStmt.setString(1, gymOwner.getPhoneNumber());
            ownerStmt.setString(2, gymOwner.getCity());
            ownerStmt.setString(3, gymOwner.getPinCode());
            ownerStmt.setString(4, gymOwner.getPanCard());
            ownerStmt.setString(5, gymOwner.getGstin());
            ownerStmt.setString(6, gymOwner.getAadharNumber());
            ownerStmt.setBoolean(7, gymOwner.getIsApproved());
            ownerStmt.setInt(8, gymOwner.getUserId());
            int ownerRows = ownerStmt.executeUpdate();

            if (userRows > 0 && ownerRows > 0) {
                System.out.println("✅ Gym owner details updated successfully for userId: " + gymOwner.getUserId());
            } else {
                System.out.println("⚠️ Update failed: No matching records found for userId: " + gymOwner.getUserId());
            }

            return gymOwner;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to update gym owner details.");
        } finally {
            try {
                if (userStmt != null) userStmt.close();
                if (ownerStmt != null) ownerStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public FlipFitGymOwner viewDetails(int gymOwnerId) {
        String ownerSql = "SELECT * FROM FlipFitGymOwner WHERE gymOwnerId = ?";
        String userSql = "SELECT * FROM FlipFitUser WHERE userId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ownerStmt = conn.prepareStatement(ownerSql);
             PreparedStatement userStmt = conn.prepareStatement(userSql)) {

            // Step 1: Fetch FlipFitGymOwner details
            ownerStmt.setInt(1, gymOwnerId);
            ResultSet ownerRs = ownerStmt.executeQuery();

            if (ownerRs.next()) {
                FlipFitGymOwner gymOwner = new FlipFitGymOwner();
                gymOwner.setUserId(ownerRs.getInt("gymOwnerId"));
                gymOwner.setPhoneNumber(ownerRs.getString("phoneNumber"));
                gymOwner.setCity(ownerRs.getString("city"));
                gymOwner.setPinCode(ownerRs.getString("pinCode"));
                gymOwner.setPanCard(ownerRs.getString("panCard"));
                gymOwner.setGstin(ownerRs.getString("gstin"));
                gymOwner.setAadharNumber(ownerRs.getString("aadharNumber"));
                gymOwner.setIsApproved(ownerRs.getBoolean("isApproved"));

                // Step 2: Fetch FlipFitUser details
                userStmt.setInt(1, gymOwnerId);
                ResultSet userRs = userStmt.executeQuery();

                if (userRs.next()) {
                    gymOwner.setUsername(userRs.getString("username"));
                    gymOwner.setEmail(userRs.getString("email"));
                    gymOwner.setPassword(userRs.getString("password"));
                    gymOwner.setRoleId(userRs.getInt("roleId"));
                }

                return gymOwner;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // if not found or error occurs
    }

    @Override
    public boolean deleteGym(int gymId) {
        String deleteGymSQL = "DELETE FROM FlipFitGym WHERE gymID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteGymSQL)) {

            stmt.setInt(1, gymId);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<FlipFitSlot> viewSlots(int gymId) {
        String selectSlotsSQL = "SELECT slotId, gymId, startTime, seatsAvailable, totalSeats FROM FlipFitSlot WHERE gymId = ?";
        List<FlipFitSlot> slots = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(selectSlotsSQL)) {

            stmt.setInt(1, gymId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FlipFitSlot slot = new FlipFitSlot();
                slot.setSlotId(rs.getInt("slotId"));
                slot.setGymId(rs.getInt("gymId"));
                slot.setStartTime(rs.getTime("startTime").toLocalTime());
                slot.setSeatsAvailable(rs.getInt("seatsAvailable"));
                slot.setTotalSeats(rs.getInt("totalSeats"));

                slots.add(slot);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return slots;
    }

    @Override
    public FlipFitGymOwner login(String gymOwnerName, String password) {
        String userQuery = "SELECT * FROM FlipFitUser WHERE username = ? AND password = ?";
        String ownerQuery = "SELECT * FROM FlipFitGymOwner WHERE gymOwnerId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement userStmt = conn.prepareStatement(userQuery)) {

            // Step 1: Validate user credentials
            userStmt.setString(1, gymOwnerName);
            userStmt.setString(2, password);

            ResultSet userRs = userStmt.executeQuery();
            if (userRs.next()) {
                int userId = userRs.getInt("userId");

                // Step 2: Fetch gym owner details
                try (PreparedStatement ownerStmt = conn.prepareStatement(ownerQuery)) {
                    ownerStmt.setInt(1, userId);
                    ResultSet ownerRs = ownerStmt.executeQuery();

                    if (ownerRs.next()) {
                        FlipFitGymOwner gymOwner = new FlipFitGymOwner();

                        // From FlipFitUser
                        gymOwner.setUserId(userId);
                        gymOwner.setUsername(userRs.getString("username"));
                        gymOwner.setEmail(userRs.getString("email"));
                        gymOwner.setPassword(userRs.getString("password"));
                        gymOwner.setRoleId(userRs.getInt("roleId"));

                        // From FlipFitGymOwner
                        gymOwner.setPhoneNumber(ownerRs.getString("phoneNumber"));
                        gymOwner.setCity(ownerRs.getString("city"));
                        gymOwner.setPinCode(ownerRs.getString("pinCode"));
                        gymOwner.setPanCard(ownerRs.getString("panCard"));
                        gymOwner.setGstin(ownerRs.getString("gstin"));
                        gymOwner.setAadharNumber(ownerRs.getString("aadharNumber"));
                        gymOwner.setIsApproved(ownerRs.getBoolean("isApproved"));

                        return gymOwner;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // login failed
    }


    @Override
    public FlipFitSlot addSlot(FlipFitSlot slot) {
        String insertSlotSQL = "INSERT INTO FlipFitSlot (gymId, startTime, seatsAvailable, totalSeats) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement slotStmt = conn.prepareStatement(insertSlotSQL, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters
            slotStmt.setInt(1, slot.getGymId());
            slotStmt.setTime(2, Time.valueOf(slot.getStartTime()));
            slotStmt.setInt(3, slot.getSeatsAvailable());
            slotStmt.setInt(4, slot.getTotalSeats());

            // Execute insert
            slotStmt.executeUpdate();

            // Retrieve generated slotId
            ResultSet rs = slotStmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedSlotId = rs.getInt(1);
                slot.setSlotId(generatedSlotId);
                return slot;
            } else {
                throw new SQLException("Failed to retrieve generated slotId.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteSlot(int slotId) {
        String deleteSlotSQL = "DELETE FROM FlipFitSlot WHERE slotId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteSlotSQL)) {

            stmt.setInt(1, slotId);
            int affectedRows = stmt.executeUpdate();

            return affectedRows > 0; // true if a row was deleted

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<FlipFitBooking> viewBookings(int gymId) {
        return List.of();
    }
}
