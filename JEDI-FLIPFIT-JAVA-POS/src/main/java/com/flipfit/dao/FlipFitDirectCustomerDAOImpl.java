package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class FlipFitDirectCustomerDAOImpl implements FlipFitDirectCustomerDAO{


    @Override
    public List<FlipFitBooking> viewBookedSlots(int userId) {
        return List.of();
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
    public FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction) {
        return null;
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
    public FlipFitBooking makeFlipFitBooking(int customerID, int slotId) {
        return null;
    }

    @Override
    public boolean cancelFlipFitBooking(int bookingId) {
        return false;
    }
}
