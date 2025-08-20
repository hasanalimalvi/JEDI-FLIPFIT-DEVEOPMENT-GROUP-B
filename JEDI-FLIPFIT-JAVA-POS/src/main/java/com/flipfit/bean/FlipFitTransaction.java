package com.flipfit.bean;

import com.flipfit.constant.ColorConstants;

public class FlipFitTransaction {
    private int transactionId;
    private int userID;
    private int paymentType;
    private String paymentInfo;

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "\n" + ColorConstants.CYAN +
                "╔════════════════════════════════════════════════╗\n" +
                "║           💳 FLIPFIT TRANSACTION INFO          ║\n" +
                "╠════════════════════════════════════════════════╣\n" +
                String.format("║ 🆔 Transaction ID : %-26s ║\n", transactionId) +
                String.format("║ 👤 User ID        : %-26s ║\n", userID) +
                String.format("║ 💰 Payment Type   : %-26s ║\n", paymentType) +
                String.format("║ 🧾 Payment Info    : %-26s ║\n", paymentInfo) +
                "╚════════════════════════════════════════════════╝" +
                ColorConstants.RESET;
    }

}
