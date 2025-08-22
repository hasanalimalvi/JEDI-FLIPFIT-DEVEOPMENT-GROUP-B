package com.flipfit.exception;

public class PaymentFailedException extends Exception {

    private String message;

    public PaymentFailedException(String bookingId) {
        this.message = "Payment already done for Booking Id: " + bookingId + ".";
    }

    public String getMessage() {
        return message;
    }
}
