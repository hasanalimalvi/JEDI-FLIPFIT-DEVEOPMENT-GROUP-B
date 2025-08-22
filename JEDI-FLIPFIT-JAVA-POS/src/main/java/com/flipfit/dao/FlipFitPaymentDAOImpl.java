package com.flipfit.dao;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.exception.PaymentFailedException;
import com.flipfit.utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class FlipFitPaymentDAOImpl implements FlipFitPaymentDAO{

    @Override
    public FlipFitTransaction processPayment(FlipFitTransaction transaction) throws PaymentFailedException {
        String checkSQL = "SELECT transactionId FROM FlipFitTransaction WHERE bookingId = ?";
        String insertSQL = "INSERT INTO FlipFitTransaction (userId, bookingId, paymentType, amount) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSQL);
             PreparedStatement insertStmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            // 🔍 Check if payment already exists for the bookingId
            checkStmt.setInt(1, transaction.getBookingId());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                throw new PaymentFailedException(String.valueOf(transaction.getBookingId()));
            }

            // ✅ Insert new transaction
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

        } catch (PaymentFailedException e) {
            // Let the exception bubble up to be handled by the service or client layer
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
