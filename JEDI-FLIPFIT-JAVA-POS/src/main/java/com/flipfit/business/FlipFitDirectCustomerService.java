package com.flipfit.business;



import com.flipfit.bean.FlipFitBooking;
import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.bean.FlipFitGym;
import com.flipfit.bean.FlipFitSlot;

import java.util.List;

public interface FlipFitDirectCustomerService {
    List<FlipFitBooking> viewBookedSlots(int userId);
    FlipFitBooking checkFlipFitBookingConflicts(int userId, int slotTime);
    FlipFitDirectCustomer viewDetails(int customerId);
    FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer);
    FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer);
    List<FlipFitGym> viewGyms();
    boolean makePayment(int customerId);
    boolean login(String customerName, String password);

    //FlipFitBookings
    FlipFitBooking makeFlipFitBooking(int customerID, int gymID, int slotId);
    boolean cancelFlipFitBooking(int bookingId);

    //Slot
    FlipFitSlot getSlotDetails(int gymId);
}
