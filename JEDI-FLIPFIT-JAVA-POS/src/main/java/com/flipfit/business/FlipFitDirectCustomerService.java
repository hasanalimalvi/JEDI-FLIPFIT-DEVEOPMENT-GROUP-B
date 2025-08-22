package com.flipfit.business;



import com.flipfit.bean.*;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.PaymentFailedException;
import com.flipfit.exception.SlotsNotAvailableException;
import com.flipfit.exception.UsernameExistsException;

import java.time.LocalDate;
import java.util.List;

public interface FlipFitDirectCustomerService {
    List<FlipFitSlotAvailability> viewSlots(int gymId, LocalDate date) throws EntityNotFoundException;
    List<FlipFitBooking> viewBookedSlots(int userId);
    FlipFitDirectCustomer viewDetails(int customerId);
    FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) throws UsernameExistsException;
    FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer);
    List<FlipFitGym> viewGyms();
    FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction) throws PaymentFailedException, EntityNotFoundException, PaymentFailedException;
    FlipFitDirectCustomer login(String customerName, String password);
    //FlipFitBookings
    FlipFitBooking makeFlipFitBooking(int customerID, int slotId, LocalDate date) throws SlotsNotAvailableException, EntityNotFoundException;
    boolean cancelFlipFitBooking(int bookingId) throws EntityNotFoundException;

}
