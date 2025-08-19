package com.flipfit.business;

import com.flipfit.bean.Booking;
import com.flipfit.bean.DirectCustomer;

import java.util.List;

public interface DirectCustomerService {
    List<Booking> viewBookedSlots(int userId);
    Booking checkBookingConflicts(int userId, int slotTime);
    DirectCustomer viewDetails(int customerId);
    DirectCustomer registerCustomer(DirectCustomer directCustomer);
    DirectCustomer editDetails(DirectCustomer directCustomer);
}
