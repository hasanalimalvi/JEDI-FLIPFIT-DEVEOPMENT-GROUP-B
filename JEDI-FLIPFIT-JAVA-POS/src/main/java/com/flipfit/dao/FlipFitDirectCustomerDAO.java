package com.flipfit.dao;

import com.flipfit.bean.*;

import java.util.List;

public interface FlipFitDirectCustomerDAO {
    List<FlipFitSlot> viewSlots(int gymId);
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
}
