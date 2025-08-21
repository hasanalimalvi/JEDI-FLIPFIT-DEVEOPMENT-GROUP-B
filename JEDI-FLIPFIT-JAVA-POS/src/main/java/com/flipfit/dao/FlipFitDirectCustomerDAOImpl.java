package com.flipfit.dao;

import com.flipfit.bean.*;

import java.util.List;

public class FlipFitDirectCustomerDAOImpl implements FlipFitDirectCustomerDAO{
    @Override
    public List<FlipFitSlot> viewSlots(int gymId) {
        return List.of();
    }

    @Override
    public List<FlipFitBooking> viewBookedSlots(int userId) {
        return List.of();
    }

    @Override
    public FlipFitDirectCustomer viewDetails(int customerId) {
        return null;
    }

    @Override
    public FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) {
        return null;
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
    public FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction) {
        return null;
    }

    @Override
    public FlipFitDirectCustomer login(String customerName, String password) {
        return null;
    }

    @Override
    public FlipFitBooking makeFlipFitBooking(int customerID, int slotId) {
        return null;
    }

    @Override
    public boolean cancelFlipFitBooking(int bookingId) {
        return false;
    }
}
