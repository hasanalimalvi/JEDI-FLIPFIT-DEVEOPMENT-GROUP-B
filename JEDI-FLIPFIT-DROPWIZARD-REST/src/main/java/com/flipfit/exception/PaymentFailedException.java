package com.flipfit.exception;

/**
 *@author : "Chanukya"
 *@parameters: "String bookingId"
 *@exceptions: "N/A"
 *@description : "Exception thrown when a payment attempt fails because a payment has already been processed for the specified booking ID."
 */
public class PaymentFailedException extends Exception {

    private String message;

    public PaymentFailedException(String bookingId) {
        this.message = "Payment failed for Booking Id: " + bookingId + ".";
    }

    public String getMessage() {
        return message;
    }
}