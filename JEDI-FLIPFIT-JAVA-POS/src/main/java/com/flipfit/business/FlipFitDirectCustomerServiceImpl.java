package com.flipfit.business;

import com.flipfit.bean.FlipFitBooking;
import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.bean.FlipFitGym;
import com.flipfit.bean.FlipFitSlot;

import java.util.ArrayList;
import java.util.List;

public class FlipFitDirectCustomerServiceImpl implements FlipFitDirectCustomerService{

    static List<FlipFitDirectCustomer> flipFitDirectCustomers = new ArrayList<FlipFitDirectCustomer>();



    @Override
    public List<FlipFitBooking> viewBookedSlots(int userId) {
        return List.of();
    }

    @Override
    public FlipFitBooking checkFlipFitBookingConflicts(int userId, int slotTime) {
        return null;
    }

    @Override
    public FlipFitDirectCustomer viewDetails(int customerId) {
        return null;
    }

    @Override
    public FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) {
        flipFitDirectCustomers.add(directCustomer);
        return directCustomer;
    }

    @Override
    public FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer) {
        return null;
    }

    @Override
    public List<FlipFitGym> viewGyms() {
        return List.of();
    }

    @Override
    public boolean makePayment(int customerId) {
        return false;
    }

    @Override
    public boolean login(String customerName, String password) {
        return false;
    }

    @Override
    public FlipFitBooking makeFlipFitBooking(int customerID, int gymID, int slotId) {
        return null;
    }

    @Override
    public boolean cancelFlipFitBooking(int bookingId) {
        return false;
    }

    @Override
    public FlipFitSlot getSlotDetails(int gymId) {
        return null;
    }


}
