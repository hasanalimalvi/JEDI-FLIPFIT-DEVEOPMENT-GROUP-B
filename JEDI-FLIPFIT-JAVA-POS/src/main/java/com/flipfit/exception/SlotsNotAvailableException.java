package com.flipfit.exception;

public class SlotsNotAvailableException extends Exception{

    String message;
    public SlotsNotAvailableException(int slotId){
        System.out.println("Slots with slotId " + slotId + " not available");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
