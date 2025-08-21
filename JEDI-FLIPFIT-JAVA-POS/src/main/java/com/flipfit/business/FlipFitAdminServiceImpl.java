package com.flipfit.business;

import com.flipfit.bean.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipFitAdminServiceImpl implements FlipFitAdminService{

    public static Map<Integer, FlipFitAdmin> flipFitAdminMap = new HashMap<Integer,FlipFitAdmin>();
    FlipFitDirectCustomerServiceImpl flipFitDirectCustomerService = new FlipFitDirectCustomerServiceImpl();
    FlipFitGymOwnerServiceImpl flipFitGymOwnerService = new FlipFitGymOwnerServiceImpl();

    @Override
    public List<FlipFitGymOwner> getPendingGymOwnerList() {

        List<FlipFitGymOwner> gymOwners = new ArrayList<>(FlipFitGymOwnerServiceImpl.flipFitGymOwnerMap.values());

        List<FlipFitGymOwner> filteredGymOwners = new ArrayList<FlipFitGymOwner>();

        for(FlipFitGymOwner flipFitGymOwner : gymOwners){
            if(!flipFitGymOwner.getIsApproved())
                filteredGymOwners.add(flipFitGymOwner);
        }

        return filteredGymOwners;
    }

    @Override
    public List<FlipFitGymOwner> getApprovedGymOwnerList() {

        List<FlipFitGymOwner> gymOwners = new ArrayList<>(FlipFitGymOwnerServiceImpl.flipFitGymOwnerMap.values());

        List<FlipFitGymOwner> filteredGymOwners = new ArrayList<FlipFitGymOwner>();

        for(FlipFitGymOwner flipFitGymOwner : gymOwners){
            if(flipFitGymOwner.getIsApproved())
                filteredGymOwners.add(flipFitGymOwner);
        }

        return filteredGymOwners;
    }

    @Override
    public List<FlipFitGym> getPendingGymList() {

        List<FlipFitGym> gyms = new ArrayList<>(FlipFitGymOwnerServiceImpl.flipFitGymMap.values());

        List<FlipFitGym> filteredGyms = new ArrayList<FlipFitGym>();

        for(FlipFitGym flipFitGym : gyms){
            if(!flipFitGym.isApproved())
                filteredGyms.add(flipFitGym);
        }

        return filteredGyms;
    }

    @Override
    public List<FlipFitGym> getApprovedGymList() {
        List<FlipFitGym> gyms = new ArrayList<>(FlipFitGymOwnerServiceImpl.flipFitGymMap.values());

        List<FlipFitGym> filteredGyms = new ArrayList<FlipFitGym>();

        for(FlipFitGym flipFitGym : gyms){
            if(flipFitGym.isApproved())
                filteredGyms.add(flipFitGym);
        }

        return filteredGyms;
    }

    @Override
    public List<FlipFitDirectCustomer> getCustomerList() {
        return new ArrayList<>(FlipFitDirectCustomerServiceImpl.flipFitDirectCustomerMap.values());
    }

    @Override
    public List<FlipFitGymOwner> getGymOwners() {
        return new ArrayList<>(FlipFitGymOwnerServiceImpl.flipFitGymOwnerMap.values());
    }

    @Override
    public List<FlipFitGym> getGyms() {
        return new ArrayList<>(FlipFitGymOwnerServiceImpl.flipFitGymMap.values());
    }

    @Override
    public boolean validateGymOwner(int gymOwnerId) {
        FlipFitGymOwner gymOwner = flipFitGymOwnerService.viewDetails(gymOwnerId);
        gymOwner.setIsApproved(true);
        flipFitGymOwnerService.editDetails(gymOwner);
        return true;
    }

    @Override
    public boolean validateGym(int gymId) {
        FlipFitGym flipFitGym = flipFitGymOwnerService.viewGym(gymId);
        flipFitGym.setApproved(true);
        flipFitGymOwnerService.updateGym(flipFitGym);
        return true;
    }

    @Override
    public FlipFitAdmin login(String adminName, String password) {
        for (FlipFitAdmin admin : flipFitAdminMap.values()) {
            if (admin.getUsername().equals(adminName) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public List<FlipFitTransaction> viewPayments() {
        List<FlipFitTransaction> payments = new ArrayList<>(FlipFitDirectCustomerServiceImpl.transactionMap.values());
        return payments;
    }
}
