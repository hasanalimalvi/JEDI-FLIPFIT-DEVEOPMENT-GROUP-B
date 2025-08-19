package com.flipfit.business;

public interface BookingService {
    Booking makeBooking(int customerID, int gymID, int slotId);
    public boolean deleteBooking(int bookingId);
}
