package com.flipfit.dao;

import com.flipfit.bean.*;
import com.flipfit.exception.EntityNotFoundException;

import java.util.List;

/**
 *@author: Akshita
 * @parameters: Not applicable for an interface Javadoc.
 * @exceptions: Not applicable for an interface Javadoc.
 * @description: This interface provides data access object (DAO) methods for managing admin-related
 *  * operations in the FlipFit  application.
 */
public interface FlipFitAdminDAO {
    List<FlipFitGymOwner> getPendingGymOwnerList();
    List<FlipFitGymOwner> getApprovedGymOwnerList();
    List<FlipFitGym> getPendingGymList();
    List<FlipFitGym> getApprovedGymList();
    List<FlipFitDirectCustomer> getCustomerList();
    List<FlipFitGymOwner> getGymOwners();
    List<FlipFitGym> getGyms();
    boolean validateGymOwner(int gymOwnerId) throws EntityNotFoundException;
    FlipFitAdmin login(String adminName, String password);
    List<FlipFitTransaction> viewPayments();
}