/**
 * @author Bhavya
 *
 * @param gymOwnerId A unique number used to identify the gym owner.
 * @param adminName The username of the admin trying to sign in.
 * @param password The password for the admin's account.
 *
 * @exception EntityNotFoundException The EntityNotFoundException will be thrown if the gym owner ID you're looking for doesn't exist in the system.
 *
 * @description This code is an interface that handles administrative tasks for the FlipFit system, including managing gyms, their owners, customers, and financial transactions.
 */

package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.exception.EntityNotFoundException;

import java.util.List;

/**
 *@author : "Bhavya"
 *@parameters: "FlipFitCustomerDAOImpl, FlipFitGymOwnerDAOImpl, FlipFitAdminDAOImpl, FlipFitPaymentDAOImpl"
 *@exceptions: "EntityNotFoundException, SQLException"
 *@description : "This interface provides data access object (DAO) methods for managing admin-related operations in the FlipFit application."
 */

public interface FlipFitAdminService {
    List<FlipFitGymOwner> getPendingGymOwnerList();
    List<FlipFitGymOwner> getApprovedGymOwnerList();
    List<FlipFitGym> getPendingGymList();
    List<FlipFitGym> getApprovedGymList();
    List<FlipFitDirectCustomer> getCustomerList();
    List<FlipFitGymOwner> getGymOwners();
    List<FlipFitGym> getGyms();
    boolean validateGymOwner(int gymOwnerId) throws EntityNotFoundException;
    boolean validateGym(int gymId) throws EntityNotFoundException;
    FlipFitAdmin login(String adminName, String password);
    List<FlipFitTransaction> viewPayments();
}
