package com.flipfit.business;



import com.flipfit.bean.*;
import com.flipfit.exception.UsernameExistsException;

import java.time.LocalDate;
import java.util.List;

public interface FlipFitDirectCustomerService {
    List<FlipFitSlotAvailability> viewSlots(int gymId, LocalDate date);
    List<FlipFitBooking> viewBookedSlots(int userId);
    FlipFitDirectCustomer viewDetails(int customerId);
    FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) throws UsernameExistsException;
    FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer);
    List<FlipFitGym> viewGyms();
    FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction);
    FlipFitDirectCustomer login(String customerName, String password);
    //FlipFitBookings
    FlipFitBooking makeFlipFitBooking(int customerID, int slotId, LocalDate date);
    boolean cancelFlipFitBooking(int bookingId);
}
