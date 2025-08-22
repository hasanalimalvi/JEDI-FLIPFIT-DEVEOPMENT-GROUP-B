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

import com.flipfit.bean.FlipFitAdmin;
import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.bean.FlipFitGym;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.client.FlipFitAdminMenu;
import com.flipfit.exception.EntityNotFoundException;

import java.util.List;

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
