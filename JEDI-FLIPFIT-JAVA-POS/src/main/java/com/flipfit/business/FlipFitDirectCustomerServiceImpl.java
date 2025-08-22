package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.constant.ColorConstants;
import com.flipfit.dao.FlipFitAdminDAO;
import com.flipfit.dao.FlipFitAdminDAOImpl;
import com.flipfit.dao.FlipFitDirectCustomerDAO;
import com.flipfit.dao.FlipFitDirectCustomerDAOImpl;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.PaymentFailedException;
import com.flipfit.exception.UsernameExistsException;

import java.time.LocalDate;
import java.util.List;

public class FlipFitDirectCustomerServiceImpl implements FlipFitDirectCustomerService{

    public static FlipFitDirectCustomer loggedInDirectCustomer = null;

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
    public FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) throws UsernameExistsException {
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
    public FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction) throws PaymentFailedException, EntityNotFoundException {
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
}
