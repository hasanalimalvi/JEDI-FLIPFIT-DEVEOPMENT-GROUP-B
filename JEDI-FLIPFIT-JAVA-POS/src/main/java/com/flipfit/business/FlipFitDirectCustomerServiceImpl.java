package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.constant.ColorConstants;
import com.flipfit.dao.FlipFitAdminDAO;
import com.flipfit.dao.FlipFitAdminDAOImpl;
import com.flipfit.dao.FlipFitDirectCustomerDAO;
import com.flipfit.dao.FlipFitDirectCustomerDAOImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipFitDirectCustomerServiceImpl implements FlipFitDirectCustomerService{

    static Map<Integer, FlipFitDirectCustomer> flipFitDirectCustomerMap = new HashMap<Integer,FlipFitDirectCustomer>();
    static int customerIdCounter = 1;
    static Map<Integer, FlipFitBooking> bookingMap = new HashMap<Integer, FlipFitBooking>();
    static int bookingIdCounter = 1;
    static Map<Integer, FlipFitTransaction> transactionMap = new HashMap<Integer, FlipFitTransaction>();
    static int transactionIdCounter = 1;

    public static FlipFitDirectCustomer loggedInDirectCustomer = null;

    static Map<Integer, FlipFitUser> userMap = new HashMap<Integer, FlipFitUser>();
    static int userIdCounter = 1;


    FlipFitDirectCustomerDAO flipFitDirectCustomerDAO = new FlipFitDirectCustomerDAOImpl();
    FlipFitPaymentService flipFitPaymentService = new FlipFitPaymentServiceImpl();
    FlipFitAdminDAO flipFitAdminDAO = new FlipFitAdminDAOImpl();



    FlipFitGymOwnerService flipFitGymOwnerService = new FlipFitGymOwnerServiceImpl();

    @Override
    public List<FlipFitSlotAvailability> viewSlots(int gymId, LocalDate date) {
        return flipFitGymOwnerService.viewSlots(gymId, date);
    }

    @Override
    public List<FlipFitBooking> viewBookedSlots(int userId) {
        return flipFitDirectCustomerDAO.viewBookedSlots(userId);
    }

    @Override
    public FlipFitDirectCustomer viewDetails(int customerId) {
        return flipFitDirectCustomerDAO.viewDetails(customerId);
    }


    @Override
    public FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) {
        FlipFitDirectCustomer customer = flipFitDirectCustomerDAO.registerCustomer(directCustomer);
        System.out.println(ColorConstants.GREEN + "✅ Customer registered successfully with ID: " + directCustomer.getUserId() + ColorConstants.RESET);
        return customer;


    }


    @Override
    public FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer) {

        FlipFitDirectCustomer flipFitDirectCustomer = flipFitDirectCustomerDAO.editDetails(directCustomer);
        return flipFitDirectCustomer;
    }

    @Override
    public List<FlipFitGym> viewGyms() {
        return flipFitAdminDAO.getGyms();
    }

    @Override
    public FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction) {
        return flipFitPaymentService.processPayment(flipFitTransaction);
    }

    @Override
    public FlipFitDirectCustomer login(String username, String password) {

        loggedInDirectCustomer = flipFitDirectCustomerDAO.login(username, password);
        return loggedInDirectCustomer;

    }

    @Override
    public FlipFitBooking makeFlipFitBooking(int customerID, int slotId, LocalDate date) {
        return flipFitDirectCustomerDAO.makeFlipFitBooking(customerID, slotId, date);
    }


    @Override
    public boolean cancelFlipFitBooking(int bookingId) {
        return flipFitDirectCustomerDAO.cancelFlipFitBooking(bookingId);
    }


    @Override
    public List<FlipFitSlot> getSlotsDetails(int gymId) {
        List<FlipFitSlot> filteredSlots = new ArrayList<>();

        for (FlipFitSlot slot : FlipFitGymOwnerServiceImpl.flipFitSlotMap.values()) {
            if (slot.getGymId() == gymId) {
                filteredSlots.add(slot);
            }
        }

        if (filteredSlots.isEmpty()) {
            System.out.println(ColorConstants.RED + "❗ No slots found for Gym ID: " + gymId + ColorConstants.RESET);
        }

        return filteredSlots;
    }
}
