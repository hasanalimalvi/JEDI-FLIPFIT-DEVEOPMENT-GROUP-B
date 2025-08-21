package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.constant.ColorConstants;
import com.flipfit.dao.FlipFitDirectCustomerDAOImpl;
import com.flipfit.dao.FlipFitGymOwnerDAO;
import com.flipfit.dao.FlipFitGymOwnerDAOImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.flipfit.business.FlipFitDirectCustomerServiceImpl.bookingMap;

public class FlipFitGymOwnerServiceImpl implements FlipFitGymOwnerService{

    private final FlipFitGymOwnerDAO flipFitGymOwnerDAO = new FlipFitGymOwnerDAOImpl();
    static Map<Integer, FlipFitGymOwner> flipFitGymOwnerMap = new HashMap<Integer,FlipFitGymOwner>();
    static Map<Integer, FlipFitGym> flipFitGymMap = new HashMap<Integer,FlipFitGym>();
    static Map<Integer, FlipFitSlot> flipFitSlotMap = new HashMap<Integer,FlipFitSlot>();
    static int gymOwnerIdCounter = 1;
    static int gymIdCounter = 1;
    static int slotIdCounter = 1;

    public static FlipFitGymOwner loggedInGymOwner = null;

    @Override
    public FlipFitGymOwner registerGymOwner(FlipFitGymOwner gymOwner) {
        return flipFitGymOwnerDAO.registerGymOwner(gymOwner);
    }

    @Override
    public FlipFitGym addGym(FlipFitGym gym) {
        return flipFitGymOwnerDAO.addGym(gym);
    }

    @Override
    public FlipFitGym updateGym(FlipFitGym gym) {
        return flipFitGymOwnerDAO.updateGym(gym);
    }

    @Override
    public FlipFitGym viewGym(int gymId) {
        return flipFitGymOwnerDAO.viewGym(gymId);
    }

    @Override
    public List<FlipFitGym> viewGyms(int gymOwnerId) {
        List<FlipFitGym> filteredGyms = new ArrayList<>();
        filteredGyms = flipFitGymOwnerDAO.viewGyms(gymOwnerId);

        if (filteredGyms.isEmpty()) {
            System.out.println(ColorConstants.RED + "❗ No gyms found for Gym Owner ID: " + gymOwnerId + ColorConstants.RESET);
        }
        return filteredGyms;
    }

    @Override
    public List<FlipFitTransaction> viewTransactions(int gymId) {
        return flipFitGymOwnerDAO.viewTransactions(gymId);
    }

    @Override
    public FlipFitGymOwner editDetails(FlipFitGymOwner gymOwner) {
        flipFitGymOwnerDAO.editDetails(gymOwner);
        return gymOwner;
    }

    @Override
    public FlipFitGymOwner viewDetails(int gymOwnerId) {
        return flipFitGymOwnerDAO.viewDetails(gymOwnerId);
    }

    @Override
    public boolean deleteGym(int gymId) {
        return flipFitGymOwnerDAO.deleteGym(gymId);
    }

    @Override
    public List<FlipFitSlotAvailability> viewSlots(int gymId, LocalDate date) {
        return flipFitGymOwnerDAO.viewSlots(gymId, date);
    }

    @Override
    public FlipFitGymOwner login(String username, String password) {
        loggedInGymOwner = flipFitGymOwnerDAO.login(username, password);
        return loggedInGymOwner;
    }

    @Override
    public FlipFitSlot addSlot(FlipFitSlot slot) {
        System.out.println(ColorConstants.GREEN + "✅ Slot added successfully with ID: "
                + slot.getSlotId() + ColorConstants.RESET);
        return flipFitGymOwnerDAO.addSlot(slot);
    }

    @Override
    public boolean deleteSlot(int slotId) {
        return flipFitGymOwnerDAO.deleteSlot(slotId);
    }

    @Override
    public List<FlipFitBooking> viewBookings(int gymId) {
        return flipFitGymOwnerDAO.viewBookings(gymId);
    }

}
