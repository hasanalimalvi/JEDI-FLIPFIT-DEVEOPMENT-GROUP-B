package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.constant.ColorConstants;
import com.flipfit.dao.FlipFitDirectCustomerDAOImpl;
import com.flipfit.dao.FlipFitGymOwnerDAO;
import com.flipfit.dao.FlipFitGymOwnerDAOImpl;

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
        return flipFitGymMap.get(gymId);
    }

    @Override
    public List<FlipFitGym> viewGyms(int gymOwnerId) {
        return flipFitGymOwnerDAO.viewGyms(gymOwnerId);
    }

    @Override
    public List<FlipFitTransaction> viewTransactions(int gymId) {
        List<FlipFitTransaction> result = new ArrayList<>();

        for (FlipFitTransaction transaction : FlipFitDirectCustomerServiceImpl.transactionMap.values()) {
            int bookingId = transaction.getBookingId();
            FlipFitBooking booking = bookingMap.get(bookingId);

            if (booking != null) {
                int slotId = booking.getSlotId();
                FlipFitSlot slot = flipFitSlotMap.get(slotId);

                if (slot != null && slot.getGymId() == gymId) {
                    result.add(transaction);
                }
            }
        }

        return result;
    }

    @Override
    public FlipFitGymOwner editDetails(FlipFitGymOwner gymOwner) {
        flipFitGymOwnerMap.put(gymOwner.getUserId(), gymOwner);
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
    public List<FlipFitSlot> viewSlots(int gymId) {
        List<FlipFitSlot> filteredSlots = new ArrayList<>();

        for (FlipFitSlot slot : flipFitSlotMap.values()) {
            if (slot.getGymId() == gymId) {
                filteredSlots.add(slot);
            }
        }

        if (filteredSlots.isEmpty()) {
            System.out.println(ColorConstants.RED + "❗ No slots found for Gym ID: " + gymId + ColorConstants.RESET);
        }

        return filteredSlots;
    }

    @Override
    public FlipFitGymOwner login(String username, String password) {
        loggedInGymOwner = flipFitGymOwnerDAO.login(username, password);
        return loggedInGymOwner;
    }

    @Override
    public FlipFitSlot addSlot(FlipFitSlot slot) {
        slot.setSlotId(slotIdCounter++);
        flipFitSlotMap.put(slot.getSlotId(), slot);
        System.out.println(ColorConstants.GREEN + "✅ Slot added successfully with ID: "
                + slot.getSlotId() + ColorConstants.RESET);
        return slot;
    }

    @Override
    public boolean deleteSlot(int slotId) {
        if (flipFitSlotMap.containsKey(slotId)) {
            flipFitSlotMap.remove(slotId);
            return true;
        }
        return false;
    }

    @Override
    public List<FlipFitBooking> viewBookings(int gymId) {
        List<FlipFitBooking> result = new ArrayList<>();

        for (FlipFitSlot slot : flipFitSlotMap.values()) {
            if (slot.getGymId() == gymId) {
                int slotId = slot.getSlotId();

                for (FlipFitBooking booking : FlipFitDirectCustomerServiceImpl.bookingMap.values()) {
                    if (booking.getSlotId() == slotId && !booking.isCancelled()) {
                        result.add(booking);
                    }
                }
            }
        }

        return result;
    }

}
