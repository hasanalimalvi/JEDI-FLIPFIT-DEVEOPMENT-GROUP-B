package com.flipfit.dao;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.PaymentFailedException;
import com.flipfit.utils.DBConnection;

import java.sql.*;

/**
 *@author : "Ain Fatima"
 *@parameters: "FlipFitCustomerDAOImpl, FlipFitGymOwnerDAOImpl, FlipFitAdminDAOImpl, FlipFitPaymentDAOImpl"
 *@exceptions: "PaymentFailedException, EntityNotFoundException, SQLException"
 *@description : "This class provides data access object (DAO) methods for managing payment-related operations in the FlipFit application, implementing the FlipFitPaymentDAO interface."
 */
public class FlipFitPaymentDAOImpl implements FlipFitPaymentDAO{

    @Override
    public FlipFitTransaction processPayment(FlipFitTransaction transaction)
            throws PaymentFailedException, EntityNotFoundException {

        String validateBookingSQL = "SELECT bookingId FROM FlipFitBooking WHERE bookingId = ?";
        String checkTransactionSQL = "SELECT transactionId FROM FlipFitTransaction WHERE bookingId = ?";
        String insertSQL = "INSERT INTO FlipFitTransaction (userId, bookingId, paymentType, amount) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement validateStmt = conn.prepareStatement(validateBookingSQL);
             PreparedStatement checkStmt = conn.prepareStatement(checkTransactionSQL);
             PreparedStatement insertStmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            // 🔍 Step 1: Validate bookingId exists
            validateStmt.setInt(1, transaction.getBookingId());
            ResultSet bookingResult = validateStmt.executeQuery();
            if (!bookingResult.next()) {
                throw new EntityNotFoundException(transaction.getBookingId(), "Booking");
            }

            // 🔍 Step 2: Check if payment already exists for the bookingId
            checkStmt.setInt(1, transaction.getBookingId());
            ResultSet transactionResult = checkStmt.executeQuery();
            if (transactionResult.next()) {
                throw new PaymentFailedException(String.valueOf(transaction.getBookingId()));
            }

            // ✅ Step 3: Insert new transaction
            insertStmt.setInt(1, transaction.getUserId());
            insertStmt.setInt(2, transaction.getBookingId());
            insertStmt.setInt(3, transaction.getPaymentType());
            insertStmt.setDouble(4, transaction.getAmount());

            int affectedRows = insertStmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    transaction.setTransactionId(generatedKeys.getInt(1));
                }
                return transaction;
            } else {
                throw new SQLException("Failed to insert transaction.");
            }

        } catch (PaymentFailedException | EntityNotFoundException e) {
            // Let custom exceptions bubble up
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}