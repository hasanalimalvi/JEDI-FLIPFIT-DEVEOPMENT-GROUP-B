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
        String sql = "SELECT * FROM FlipFitGymOwner WHERE gymOwnerId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, gymOwnerId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                FlipFitGymOwner gymOwner = new FlipFitGymOwner();
                gymOwner.setUserId(rs.getInt("gymOwnerId"));
                gymOwner.setPhoneNumber(rs.getString("phoneNumber"));
                gymOwner.setCity(rs.getString("city"));
                gymOwner.setPinCode(rs.getString("pinCode"));
                gymOwner.setPanCard(rs.getString("panCard"));
                gymOwner.setGstin(rs.getString("gstin"));
                gymOwner.setAadharNumber(rs.getString("aadharNumber"));
                gymOwner.setIsApproved(rs.getBoolean("isApproved"));

                return gymOwner;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // if not found or error occurs
    }

    @Override
    public boolean deleteGym(int gymId) {
        return false;
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
