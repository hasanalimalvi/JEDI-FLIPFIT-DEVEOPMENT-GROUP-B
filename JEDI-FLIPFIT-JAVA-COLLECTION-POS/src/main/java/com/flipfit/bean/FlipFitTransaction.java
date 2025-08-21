package com.flipfit.bean;

import com.flipfit.constant.ColorConstants;

public class FlipFitTransaction {
    private int transactionId;
    private int userId;
    private int bookingId;
    private int paymentType;
    private double amount;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "\n" + ColorConstants.CYAN +
                "╔════════════════════════════════════════════════╗\n" +
                "║           💳 FLIPFIT TRANSACTION INFO          ║\n" +
                "╠════════════════════════════════════════════════╣\n" +
                String.format("║ 🆔 Transaction ID : %-26s ║\n", transactionId) +
                String.format("║ 👤 User ID        : %-26s ║\n", userId) +
                String.format("║ 📌 Booking ID     : %-26s ║\n", bookingId) +
                String.format("║ 💰 Payment Type   : %-26s ║\n", paymentType) +
                String.format("║ 💵 Amount         : ₹%-25.2f ║\n", amount) +
                "╚════════════════════════════════════════════════╝" +
                ColorConstants.RESET;
    }


}
