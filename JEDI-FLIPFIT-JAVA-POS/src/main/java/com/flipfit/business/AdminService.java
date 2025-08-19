package com.flipfit.business;

public interface AdminService {
    List<GymOwner> getPendingGymOwnerList();
    List<GymOwner> getApprovedGymOwnerList();
    List<Gym> getPendingGymList();
    List<Gym> getApprovedGymList();
    List<DirectCustomer> getCustomerList();
    List<Gym> getGymsUsingOwnerId(int gymOwnerId);
    boolean validateGymOwner(int gymOwnerId);
    boolean validateGym(int gymId);
}
