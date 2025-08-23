package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.UsernameExistsException;

import java.time.LocalDate;
import java.util.List;
/**
 *@Author : "Akshita"
 *@Parameters: "FlipFitCustomerDAOImpl, FlipFitGymOwnerDAOImpl, FlipFitAdminDAOImpl, FlipFitPaymentDAOImpl"
 *@Exceptions: "EntityNotFoundException, SQLException"
 *@Description : "This interface provides data access object (DAO) methods for managing admin-related operations in the FlipFit application."
 */

public interface FlipFitGymOwnerService {
    FlipFitGymOwner registerGymOwner(FlipFitGymOwner gymOwner) throws UsernameExistsException;
    FlipFitGym addGym(FlipFitGym gym);
    FlipFitGym updateGym(FlipFitGym gym);
    FlipFitGym viewGym(int gymId) throws EntityNotFoundException;
    List<FlipFitGym> viewGyms(int gymOwnerId);
    List<FlipFitTransaction> viewTransactions(int gymId) throws EntityNotFoundException;
    FlipFitGymOwner editDetails(FlipFitGymOwner gymOwner);
    FlipFitGymOwner viewDetails(int gymOwnerId);

    boolean deleteGym(int gymId) throws EntityNotFoundException;
    List<FlipFitSlotAvailability> viewSlots(int gymId, LocalDate date) throws EntityNotFoundException;

    FlipFitGymOwner login(String username, String password);

    //Slot
    FlipFitSlot addSlot(FlipFitSlot slot);
    boolean deleteSlot(int slotId) throws EntityNotFoundException;
    List<FlipFitBooking> viewBookings(int gymId) throws EntityNotFoundException;
}
