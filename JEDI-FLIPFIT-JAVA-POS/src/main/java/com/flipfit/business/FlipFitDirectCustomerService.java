package com.flipfit.business;



import com.flipfit.bean.*;

import java.time.LocalDate;
import java.util.List;

public interface FlipFitDirectCustomerService {
    List<FlipFitSlotAvailability> viewSlots(int gymId, LocalDate date);
    List<FlipFitBooking> viewBookedSlots(int userId);
    FlipFitDirectCustomer viewDetails(int customerId);
    FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer);
    FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer);
    List<FlipFitGym> viewGyms();
    FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction);
    FlipFitDirectCustomer login(String customerName, String password);
    //FlipFitBookings
    FlipFitBooking makeFlipFitBooking(int customerID, int slotId);
    boolean cancelFlipFitBooking(int bookingId);

    //Slot
    List<FlipFitSlot> getSlotsDetails(int gymId);
}
