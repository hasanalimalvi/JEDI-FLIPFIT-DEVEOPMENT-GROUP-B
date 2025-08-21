package com.flipfit.dao;

import com.flipfit.bean.*;

import java.util.List;

public class FlipFitAdminDAOImpl implements FlipFitAdminDAO{
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
        return List.of();
    }

    @Override
    public List<FlipFitGymOwner> getGymOwners() {
        return List.of();
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
    public FlipFitAdmin login(String adminName, String password) {
        return null;
    }

    @Override
    public List<FlipFitTransaction> viewPayments() {
        return List.of();
    }
}
