/**
 * @author Shalini
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
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.PaymentFailedException;
import com.flipfit.exception.SlotsNotAvailableException;
import com.flipfit.exception.UsernameExistsException;

import java.time.LocalDate;
import java.util.List;

/**
 *@Author : "Chanukya"
 *@Parameters: "FlipFitCustomerDAOImpl, FlipFitGymOwnerDAOImpl, FlipFitAdminDAOImpl, FlipFitPaymentDAOImpl"
 *@Exceptions: "EntityNotFoundException, SQLException"
 *@Description : "This interface provides data access object (DAO) methods for managing admin-related operations in the Flipfit application."
 */

public interface FlipFitDirectCustomerService {
    List<FlipFitSlotAvailability> viewSlots(int gymId, LocalDate date) throws EntityNotFoundException;
    List<FlipFitBooking> viewBookedSlots(int userId);
    FlipFitDirectCustomer viewDetails(int customerId);
    FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) throws UsernameExistsException;
    FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer);
    List<FlipFitGym> viewGyms();
    FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction) throws EntityNotFoundException, PaymentFailedException;
    FlipFitDirectCustomer login(String customerName, String password);
    //FlipFitBookings
    FlipFitBooking makeFlipFitBooking(int customerID, int slotId, LocalDate date) throws SlotsNotAvailableException, EntityNotFoundException;
    boolean cancelFlipFitBooking(int bookingId) throws EntityNotFoundException;

}
