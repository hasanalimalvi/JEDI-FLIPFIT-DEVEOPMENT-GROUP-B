package com.flipfit.business;

import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.bean.FlipFitGym;
import com.flipfit.bean.FlipFitGymOwner;

import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminServiceImpl implements FlipFitAdminService{

    FlipFitDirectCustomerServiceImpl flipFitDirectCustomerService = new FlipFitDirectCustomerServiceImpl();

    @Override
    public List<FlipFitGymOwner> getPendingGymOwnerList() {
        return List.of();
    }

    @Override
    public List<FlipFitGymOwner> getApprovedGymOwnerList() {
        return List.of();
    }

    @Override
    public List<FlipFitGym> getPendingGymList() {
        return List.of();
    }

    @Override
    public List<FlipFitGym> getApprovedGymList() {
        return List.of();
    }

    @Override
    public List<FlipFitDirectCustomer> getCustomerList() {
        return new ArrayList<>(FlipFitDirectCustomerServiceImpl.flipFitDirectCustomerMap.values());
    }

    @Override
    public List<FlipFitGymOwner> getGymOwners() {
        return new ArrayList<>(FlipFitGymOwnerServiceImpl.flipFitDirectCustomerMap.values());
    }

    @Override
    public List<FlipFitGym> getGyms() {
        return List.of();
    }

    @Override
    public boolean validateGymOwner(int gymOwnerId) {
        return false;
    }

    @Override
    public boolean validateGym(int gymId) {
        return false;
    }

    @Override
    public boolean login(String adminName, String password) {
        return false;
    }
}
