package com.flipfit.business;

import com.flipfit.bean.DirectCustomer;
import com.flipfit.bean.Gym;
import com.flipfit.bean.GymOwner;

import java.util.List;

public interface AdminService {
    List<GymOwner> getPendingGymOwnerList();
    List<GymOwner> getApprovedGymOwnerList();
    List<Gym> getPendingGymList();
    List<Gym> getApprovedGymList();
    List<DirectCustomer> getCustomerList();
    List<GymOwner> getGymOwners();
    List<Gym> getGyms();
    boolean validateGymOwner(int gymOwnerId);
    boolean validateGym(int gymId);
}
