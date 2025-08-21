package com.flipfit.dao;

import com.flipfit.bean.*;

import java.time.LocalDate;
import java.util.List;

public interface FlipFitDirectCustomerDAO {
    List<FlipFitBooking> viewBookedSlots(int userId);
    FlipFitDirectCustomer viewDetails(int customerId);
    FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer);
    FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer);

    FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction);
    FlipFitDirectCustomer login(String customerName, String password);
    //FlipFitBookings
    FlipFitBooking makeFlipFitBooking(int customerID, int slotId);
    boolean cancelFlipFitBooking(int bookingId);
}
