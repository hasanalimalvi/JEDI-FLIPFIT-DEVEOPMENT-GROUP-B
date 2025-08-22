package com.flipfit.dao;

import com.flipfit.bean.FlipFitAdmin;
import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.bean.FlipFitGym;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.exception.EntityNotFoundException;

import java.util.List;

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
