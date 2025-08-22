package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.exception.UsernameExistsException;
import com.flipfit.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlipFitDirectCustomerDAOImpl implements FlipFitDirectCustomerDAO{


    @Override
    public List<FlipFitBooking> viewBookedSlots(int userId) {
        List<FlipFitBooking> bookings = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT bookingId, userId, slotId, isCancelled, date FROM FlipFitBooking WHERE userId = ? AND isCancelled = false";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                FlipFitBooking booking = new FlipFitBooking();
                booking.setBookingId(rs.getInt("bookingId"));
                booking.setUserId(rs.getInt("userId"));
                booking.setSlotId(rs.getInt("slotId"));
                booking.setCancelled(rs.getBoolean("isCancelled"));
                booking.setDate(rs.getDate("date").toLocalDate());

                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch bookings for userId: " + userId, e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookings;
    }



    @Override
    public FlipFitDirectCustomer viewDetails(int customerId) {
        FlipFitDirectCustomer customer = null;
        // SQL query to retrieve customer details by customerId
        // Join with FlipFitUser to get common user details like username, email, password, and roleId
        String sql = "SELECT fc.customerId, fu.username, fu.email, fu.password, fu.roleId, " +
                "fc.phoneNumber, fc.city, fc.pinCode " +
                "FROM FlipFitDirectCustomer fc " +
                "JOIN FlipFitUser fu ON fc.customerId = fu.userId " +
                "WHERE fc.customerId = ?";

        // Use try-with-resources to ensure Connection, PreparedStatement, and ResultSet are closed.
        try (Connection conn = DBConnection.getConnection(); // Get database connection from DBConnection utility
             PreparedStatement pstmt = conn.prepareStatement(sql)) { // Prepare the SQL statement

            // Set the customerId parameter for the prepared statement.
            // '1' refers to the first '?' in the SQL query.
            pstmt.setInt(1, customerId);

            try (ResultSet rs = pstmt.executeQuery()) { // Execute the query and get the result set
                if (rs.next()) { // Check if a record was found
                    // Retrieve data from the ResultSet using column names
                    int retrievedCustomerId = rs.getInt("customerId");
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    int roleId = rs.getInt("roleId");
                    String phoneNumber = rs.getString("phoneNumber");
                    String city = rs.getString("city");
                    String pinCode = rs.getString("pinCode");

                    // Create a new FlipFitDirectCustomer object with the retrieved data
                    customer = new FlipFitDirectCustomer();
customer.setUserId(retrievedCustomerId);
customer.setUsername(username);
customer.setEmail(email);
customer.setPassword(password);
customer.setRoleId(roleId);
customer.setPhoneNumber(phoneNumber);
customer.setCity(city);
customer.setPinCode(pinCode);


                }
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions (e.g., connection issues, invalid query, database errors)
            System.err.println("Database error while retrieving customer details for ID " + customerId + ": " + e.getMessage());
            e.printStackTrace(); // Log the full stack trace for debugging
        }
        return customer;

//        return null;
    }

    @Override
    public FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) throws UsernameExistsException {
        String checkUsernameSQL = "SELECT COUNT(*) FROM FlipFitUser WHERE username = ?";
        String insertUserSQL = "INSERT INTO FlipFitUser (username, email, password, roleId) VALUES (?, ?, ?, ?)";
        String insertCustomerSQL = "INSERT INTO FlipFitDirectCustomer (customerId, phoneNumber, city, pinCode) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkUsernameSQL);
             PreparedStatement userStmt = conn.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement customerStmt = conn.prepareStatement(insertCustomerSQL)) {

            // 🔍 Check if username already exists
            checkStmt.setString(1, directCustomer.getUsername());
            ResultSet checkResult = checkStmt.executeQuery();
            if (checkResult.next() && checkResult.getInt(1) > 0) {
                throw new UsernameExistsException(directCustomer.getUsername());
            }

            // ✅ Insert into FlipFitUser
            userStmt.setString(1, directCustomer.getUsername());
            userStmt.setString(2, directCustomer.getEmail());
            userStmt.setString(3, directCustomer.getPassword());
            userStmt.setInt(4, directCustomer.getRoleId());
            userStmt.executeUpdate();

            // 🔑 Get generated userId
            ResultSet rs = userStmt.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1);
                directCustomer.setUserId(userId);

                // 🧍 Insert into FlipFitDirectCustomer
                customerStmt.setInt(1, userId);
                customerStmt.setString(2, directCustomer.getPhoneNumber());
                customerStmt.setString(3, directCustomer.getCity());
                customerStmt.setString(4, directCustomer.getPinCode());
                customerStmt.executeUpdate();

                return directCustomer;
            } else {
                throw new SQLException("User ID generation failed.");
            }

        } catch (UsernameExistsException e) {
            // Let the exception bubble up to be handled by the service or client layer
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer) {
        Connection conn = null;
        PreparedStatement userStmt = null;
        PreparedStatement customerStmt = null;

        try {
            conn = DBConnection.getConnection();

            // Step 1: Update FlipFitUser table
            String updateUserQuery = "UPDATE FlipFitUser SET username = ?, email = ?, password = ?, roleId = ? WHERE userId = ?";
            userStmt = conn.prepareStatement(updateUserQuery);
            userStmt.setString(1, directCustomer.getUsername());
            userStmt.setString(2, directCustomer.getEmail());
            userStmt.setString(3, directCustomer.getPassword());
            userStmt.setInt(4, directCustomer.getRoleId());
            userStmt.setInt(5, directCustomer.getUserId());
            int userRows = userStmt.executeUpdate();

            // Step 2: Update FlipFitDirectCustomer table
            String updateCustomerQuery = "UPDATE FlipFitDirectCustomer SET phoneNumber = ?, city = ?, pinCode = ? WHERE customerId = ?";
            customerStmt = conn.prepareStatement(updateCustomerQuery);
            customerStmt.setString(1, directCustomer.getPhoneNumber());
            customerStmt.setString(2, directCustomer.getCity());
            customerStmt.setString(3, directCustomer.getPinCode());
            customerStmt.setInt(4, directCustomer.getUserId());
            int customerRows = customerStmt.executeUpdate();

            if (userRows > 0 && customerRows > 0) {
                System.out.println("✅ User details updated successfully for userId: " + directCustomer.getUserId());
            } else {
                System.out.println("⚠️ Update failed: No matching records found for userId: " + directCustomer.getUserId());
            }

            return directCustomer;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to update user details.");
        } finally {
            try {
                if (userStmt != null) userStmt.close();
                if (customerStmt != null) customerStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public FlipFitDirectCustomer login(String customerName, String password) {
        String userQuery = "SELECT * FROM FlipFitUser WHERE username = ? AND password = ?";
        String customerQuery = "SELECT * FROM FlipFitDirectCustomer WHERE customerId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement userStmt = conn.prepareStatement(userQuery)) {

            // Step 1: Validate credentials from FlipFitUser
            userStmt.setString(1, customerName);
            userStmt.setString(2, password);

            ResultSet userRs = userStmt.executeQuery();
            if (userRs.next()) {
                int userId = userRs.getInt("userId");

                // Step 2: Fetch customer-specific details
                try (PreparedStatement customerStmt = conn.prepareStatement(customerQuery)) {
                    customerStmt.setInt(1, userId);
                    ResultSet customerRs = customerStmt.executeQuery();

                    if (customerRs.next()) {
                        FlipFitDirectCustomer customer = new FlipFitDirectCustomer();

                        // Populate FlipFitUser fields
                        customer.setUserId(userId);
                        customer.setUsername(userRs.getString("username"));
                        customer.setEmail(userRs.getString("email"));
                        customer.setPassword(userRs.getString("password"));
                        customer.setRoleId(userRs.getInt("roleId"));

                        // Populate FlipFitDirectCustomer fields
                        customer.setPhoneNumber(customerRs.getString("phoneNumber"));
                        customer.setCity(customerRs.getString("city"));
                        customer.setPinCode(customerRs.getString("pinCode"));

                        return customer;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this properly in production
        }

        return null; // Login failed
    }


    @Override
    public FlipFitBooking makeFlipFitBooking(int customerID, int slotId, LocalDate date) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // Step 1: Check availability
            String checkAvailabilitySQL = "SELECT seatsAvailable FROM FlipFitSlotAvailability WHERE slotId = ? AND date = ?";
            ps = conn.prepareStatement(checkAvailabilitySQL);
            ps.setInt(1, slotId);
            ps.setDate(2, java.sql.Date.valueOf(date));
            rs = ps.executeQuery();

            int seatsAvailable = -1;
            boolean availabilityExists = false;

            if (rs.next()) {
                seatsAvailable = rs.getInt("seatsAvailable");
                availabilityExists = true;
            }

            rs.close();
            ps.close();

            // Step 2: If no availability row, insert one using totalSeats
            if (!availabilityExists) {
                String getTotalSeatsSQL = "SELECT totalSeats FROM FlipFitSlot WHERE slotId = ?";
                ps = conn.prepareStatement(getTotalSeatsSQL);
                ps.setInt(1, slotId);
                rs = ps.executeQuery();

                if (!rs.next()) {
                    conn.rollback();
                    throw new RuntimeException("Slot not found");
                }

                seatsAvailable = rs.getInt("totalSeats");
                rs.close();
                ps.close();

                String insertAvailabilitySQL = "INSERT INTO FlipFitSlotAvailability (slotId, date, seatsAvailable) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(insertAvailabilitySQL);
                ps.setInt(1, slotId);
                ps.setDate(2, java.sql.Date.valueOf(date));
                ps.setInt(3, seatsAvailable);
                ps.executeUpdate();
                ps.close();
            }

            // Step 3: Check if seats are available
            if (seatsAvailable <= 0) {
                conn.rollback();
                throw new RuntimeException("No seats available");
            }

            // Step 4: Decrease seat count
            String updateSeatsSQL = "UPDATE FlipFitSlotAvailability SET seatsAvailable = seatsAvailable - 1 WHERE slotId = ? AND date = ?";
            ps = conn.prepareStatement(updateSeatsSQL);
            ps.setInt(1, slotId);
            ps.setDate(2, java.sql.Date.valueOf(date));
            ps.executeUpdate();
            ps.close();

            // Step 5: Insert booking
            String insertBookingSQL = "INSERT INTO FlipFitBooking (userId, slotId, isCancelled, date) VALUES (?, ?, false, ?)";
            ps = conn.prepareStatement(insertBookingSQL, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, customerID);
            ps.setInt(2, slotId);
            ps.setDate(3, java.sql.Date.valueOf(date)); // <-- new line
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            int bookingId = -1;
            if (rs.next()) {
                bookingId = rs.getInt(1);
            }

            conn.commit(); // End transaction

            FlipFitBooking booking = new FlipFitBooking();
            booking.setBookingId(bookingId);
            booking.setUserId(customerID);
            booking.setSlotId(slotId);
            booking.setCancelled(false);
            booking.setDate(date);

            return booking;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new RuntimeException("Booking failed: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean cancelFlipFitBooking(int bookingId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // Step 1: Get booking details
            String getBookingSQL = "SELECT slotId, date, isCancelled FROM FlipFitBooking WHERE bookingId = ?";
            ps = conn.prepareStatement(getBookingSQL);
            ps.setInt(1, bookingId);
            rs = ps.executeQuery();

            if (!rs.next()) {
                conn.rollback();
                return false; // Booking not found
            }

            int slotId = rs.getInt("slotId");
            LocalDate date = rs.getDate("date").toLocalDate();
            boolean alreadyCancelled = rs.getBoolean("isCancelled");

            rs.close();
            ps.close();

            if (alreadyCancelled) {
                conn.rollback();
                return false; // Already cancelled
            }

            // Step 2: Update booking to cancelled (set isCancelled = true)
            String cancelBookingSQL = "UPDATE FlipFitBooking SET isCancelled = true WHERE bookingId = ?";
            ps = conn.prepareStatement(cancelBookingSQL);
            ps.setInt(1, bookingId);
            ps.executeUpdate();
            ps.close();

            // Step 3: Increment seat availability
            String updateSeatsSQL = "UPDATE FlipFitSlotAvailability SET seatsAvailable = seatsAvailable + 1 WHERE slotId = ? AND date = ?";
            ps = conn.prepareStatement(updateSeatsSQL);
            ps.setInt(1, slotId);
            ps.setDate(2, java.sql.Date.valueOf(date));
            ps.executeUpdate();

            conn.commit(); // End transaction
            return true;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
