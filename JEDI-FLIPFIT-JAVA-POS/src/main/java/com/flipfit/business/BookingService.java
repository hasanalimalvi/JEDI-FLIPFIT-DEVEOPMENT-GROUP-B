package com.flipfit.business;

import com.flipfit.bean.Booking;

public interface BookingService {
    Booking makeBooking(int customerID, int gymID, int slotId);
    boolean cancelBooking(int bookingId);
}
