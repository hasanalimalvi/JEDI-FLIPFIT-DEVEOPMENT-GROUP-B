package com.flipfit.business;

import com.flipfit.bean.FlipFitBooking;
import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.bean.FlipFitGym;
import com.flipfit.bean.FlipFitSlot;
import com.flipfit.constant.ColorConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipFitDirectCustomerServiceImpl implements FlipFitDirectCustomerService{

    static Map<Integer, FlipFitDirectCustomer> flipFitDirectCustomerMap = new HashMap<Integer,FlipFitDirectCustomer>();
    static int customerIdCounter = 1;



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
        FlipFitDirectCustomer customer = flipFitDirectCustomerMap.get(customerId);
        if (customer == null) {
            System.out.println(ColorConstants.RED + "❗ Customer ID " + customerId + " not found." + ColorConstants.RESET);
        }
        return customer;
    }


    @Override
    public FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) {
        directCustomer.setUserId(customerIdCounter++);
        flipFitDirectCustomerMap.put(directCustomer.getUserId(), directCustomer);
        System.out.println(ColorConstants.GREEN + "✅ Customer registered successfully with ID: " + directCustomer.getUserId() + ColorConstants.RESET);
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
