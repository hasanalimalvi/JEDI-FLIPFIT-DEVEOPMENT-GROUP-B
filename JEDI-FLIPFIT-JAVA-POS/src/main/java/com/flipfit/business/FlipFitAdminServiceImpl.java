package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.bean.FlipFitAdmin;
import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.bean.FlipFitGym;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.dao.FlipFitAdminDAO;
import com.flipfit.dao.FlipFitAdminDAOImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipFitAdminServiceImpl implements FlipFitAdminService{

    public static Map<Integer, FlipFitAdmin> flipFitAdminMap = new HashMap<Integer,FlipFitAdmin>();
    FlipFitDirectCustomerServiceImpl flipFitDirectCustomerService = new FlipFitDirectCustomerServiceImpl();
    FlipFitGymOwnerServiceImpl flipFitGymOwnerService = new FlipFitGymOwnerServiceImpl();
    private final FlipFitAdminDAO flipFitAdminDAO = new FlipFitAdminDAOImpl();
    public static FlipFitAdmin loggedInAdmin = null;
    @Override
    public List<FlipFitGymOwner> getPendingGymOwnerList() {
        List<FlipFitGymOwner> pendingOwners = flipFitAdminDAO.getPendingGymOwnerList();

        if (pendingOwners.isEmpty()) {
            System.out.println("✅ No pending gym owners found.");
        }

        return pendingOwners;
    }


    @Override
    public List<FlipFitGymOwner> getApprovedGymOwnerList() {
        List<FlipFitGymOwner> approvedOwners = flipFitAdminDAO.getApprovedGymOwnerList();

        if (approvedOwners.isEmpty()) {
            System.out.println("No Approved gym owners found.");
        }

        return approvedOwners;
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
    public FlipFitAdmin login(String username, String password) {
        loggedInAdmin = flipFitAdminDAO.login(username, password);
        return loggedInAdmin;
    }

    @Override
    public List<FlipFitTransaction> viewPayments() {
        List<FlipFitTransaction> payments = new ArrayList<>(FlipFitDirectCustomerServiceImpl.transactionMap.values());
        return payments;
    }
}
