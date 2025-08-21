package com.flipfit.dao;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class FlipFitPaymentDAOImpl implements FlipFitPaymentDAO{

    @Override
    public FlipFitTransaction processPayment(FlipFitTransaction transaction) {
        String insertSQL = "INSERT INTO FlipFitTransaction (userId, bookingId, paymentType, amount) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, transaction.getUserId());
            stmt.setInt(2, transaction.getBookingId());
            stmt.setInt(3, transaction.getPaymentType());
            stmt.setDouble(4, transaction.getAmount());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    transaction.setTransactionId(rs.getInt(1));
                }
                return transaction;
            } else {
                throw new SQLException("Failed to insert transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
