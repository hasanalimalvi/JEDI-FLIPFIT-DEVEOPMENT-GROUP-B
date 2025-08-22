package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.dao.FlipFitAdminDAO;
import com.flipfit.dao.FlipFitAdminDAOImpl;
import com.flipfit.exception.EntityNotFoundException;

import java.util.List;

public class FlipFitAdminServiceImpl implements FlipFitAdminService{



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

        List<FlipFitGym> pendingGyms = flipFitAdminDAO.getPendingGymList();

        if(pendingGyms.isEmpty()){
            System.out.println("✅ No Pending gym found.");
        }

        return pendingGyms;
    }

    @Override
    public List<FlipFitGym> getApprovedGymList() {

        List<FlipFitGym> approvedGyms = flipFitAdminDAO.getApprovedGymList();

        if(approvedGyms.isEmpty()){
            System.out.println("No Approved gym found.");
        }

        return approvedGyms;
    }

    @Override
    public List<FlipFitDirectCustomer> getCustomerList() {
        return flipFitAdminDAO.getCustomerList();
    }

    @Override
    public List<FlipFitGymOwner> getGymOwners() {
        return flipFitAdminDAO.getGymOwners();
    }

    @Override
    public List<FlipFitGym> getGyms() {
        return flipFitAdminDAO.getGyms();
        // return new ArrayList<>(FlipFitGymOwnerServiceImpl.flipFitGymMap.values());
    }

    @Override
    public boolean validateGymOwner(int gymOwnerId) throws EntityNotFoundException {
        return flipFitAdminDAO.validateGymOwner(gymOwnerId);
    }

    @Override
    public boolean validateGym(int gymId) throws EntityNotFoundException {
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
       return flipFitAdminDAO.viewPayments();
    }
}
