package com.flipfit.exception;

import java.time.LocalDate;

public class SlotsNotAvailableException extends Exception {
    private String message;

    public SlotsNotAvailableException(int slotId, LocalDate date) {
        this.message = "Slot :'" + slotId + "' on '" + date.toString() + "' is not available.";
    }

    public String getMessage() {

        return message;
    }
}
