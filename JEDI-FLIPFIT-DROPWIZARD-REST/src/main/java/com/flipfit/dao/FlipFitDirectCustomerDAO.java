package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.SlotsNotAvailableException;
import com.flipfit.exception.UsernameExistsException;

import java.time.LocalDate;
import java.util.List;

/**
 *@author : "Shalini"
 *@parameters: "FlipFitCustomerDAOImpl, FlipFitGymOwnerDAOImpl, FlipFitAdminDAOImpl, FlipFitPaymentDAOImpl, FlipFitDirectCustomerDAOImpl"
 *@exceptions: "UsernameExistsException, EntityNotFoundException"
 *@description : "This interface provides data access object (DAO) methods for managing customer-related operations in the FlipFit application."
 */
public interface FlipFitDirectCustomerDAO {
    List<FlipFitBooking> viewBookedSlots(int userId);
    FlipFitDirectCustomer viewDetails(int customerId);
    FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) throws UsernameExistsException;
    FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer);

    FlipFitDirectCustomer login(String customerName, String password);
    //FlipFitBookings
    FlipFitBooking makeFlipFitBooking(int customerID, int slotId, LocalDate date) throws SlotsNotAvailableException, EntityNotFoundException;
    boolean cancelFlipFitBooking(int bookingId) throws EntityNotFoundException;
}
