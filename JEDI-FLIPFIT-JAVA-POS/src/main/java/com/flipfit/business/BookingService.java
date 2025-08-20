package com.flipfit.business;

import com.flipfit.bean.FlipFitBooking;

public interface BookingService {
    FlipFitBooking makeBooking(int customerID, int gymID, int slotId);
    boolean cancelBooking(int bookingId);
}
