package com.flipfit.business;

import com.flipfit.bean.FlipFitBooking;
import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.bean.FlipFitGym;

import java.util.List;

public interface DirectCustomerService {
    List<FlipFitBooking> viewBookedSlots(int userId);
    FlipFitBooking checkBookingConflicts(int userId, int slotTime);
    FlipFitDirectCustomer viewDetails(int customerId);
    FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer);
    FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer);
    List<FlipFitGym> viewGyms();
    boolean makePayment(int customerId);
}
