/**
 * @author Harshil
 *
 * @param customerId The unique identifier of the direct customer.
 * @param username The username of the customer attempting to log in or register.
 * @param password The password of the customer attempting to log in.
 * @param gymId The unique identifier of the gym for viewing slots or gyms.
 * @param date The specific date for which slots are being viewed.
 * @param slotId The unique identifier of a gym slot for booking.
 * @param bookingId The unique identifier of a booking for cancellation.
 * @param directCustomer An object containing details of the direct customer for registration or editing.
 * @param flipFitTransaction An object containing details of a transaction for payment processing.
 *
 * @exception EntityNotFoundException Thrown if a specified gym, customer, or other entity is not found.
 * @exception SlotsNotAvailableException Thrown if the requested slots are not available for booking.
 * @exception PaymentFailedException Thrown if a payment transaction fails.
 * @exception UsernameExistsException Thrown if an attempt is made to register a customer with an existing username.
 *
 * @description This class implements the FlipFitDirectCustomerService interface, providing the business logic for direct customer operations in the FlipFit system. It manages customer registration, login, viewing gym details and slots, booking and cancelling slots, editing personal details, and processing payments.
 */
package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.constant.ColorConstants;
import com.flipfit.dao.FlipFitAdminDAO;
import com.flipfit.dao.FlipFitAdminDAOImpl;
import com.flipfit.dao.FlipFitDirectCustomerDAO;
import com.flipfit.dao.FlipFitDirectCustomerDAOImpl;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.SlotsNotAvailableException;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.PaymentFailedException;
import com.flipfit.exception.UsernameExistsException;

import java.time.LocalDate;
import java.util.List;

/**
 *@Author : "Shalini"
 *@Parameters: "FlipFitCustomerDAOImpl, FlipFitGymOwnerDAOImpl, FlipFitAdminDAOImpl, FlipFitPaymentDAOImpl"
 *@Exceptions: "EntityNotFoundException, SQLException"
 *@Description : "This interface provides data access object (DAO) methods for managing admin-related operations in the FlipFit application."
 */

public class FlipFitDirectCustomerServiceImpl implements FlipFitDirectCustomerService{

    public static FlipFitDirectCustomer loggedInDirectCustomer = null;

    FlipFitDirectCustomerDAO flipFitDirectCustomerDAO = new FlipFitDirectCustomerDAOImpl();
    FlipFitPaymentService flipFitPaymentService = new FlipFitPaymentServiceImpl();
    FlipFitAdminDAO flipFitAdminDAO = new FlipFitAdminDAOImpl();

    FlipFitGymOwnerService flipFitGymOwnerService = new FlipFitGymOwnerServiceImpl();

    @Override
    public List<FlipFitSlotAvailability> viewSlots(int gymId, LocalDate date) throws EntityNotFoundException {
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
    public FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction) throws EntityNotFoundException, PaymentFailedException {
        return flipFitPaymentService.processPayment(flipFitTransaction);
    }

    @Override
    public FlipFitDirectCustomer login(String username, String password) {

        loggedInDirectCustomer = flipFitDirectCustomerDAO.login(username, password);
        return loggedInDirectCustomer;

    }

    @Override
    public FlipFitBooking makeFlipFitBooking(int customerID, int slotId, LocalDate date) throws SlotsNotAvailableException, EntityNotFoundException {
        return flipFitDirectCustomerDAO.makeFlipFitBooking(customerID, slotId, date);
    }


    @Override
    public boolean cancelFlipFitBooking(int bookingId) throws EntityNotFoundException {
        return flipFitDirectCustomerDAO.cancelFlipFitBooking(bookingId);
    }
}
