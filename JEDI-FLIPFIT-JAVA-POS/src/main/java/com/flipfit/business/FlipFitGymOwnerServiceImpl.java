package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.constant.ColorConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipFitGymOwnerServiceImpl implements FlipFitGymOwnerService{


    static Map<Integer, FlipFitGymOwner> flipFitGymOwnerMap = new HashMap<Integer,FlipFitGymOwner>();
    static Map<Integer, FlipFitGym> flipFitGymMap = new HashMap<Integer,FlipFitGym>();
    static Map<Integer, FlipFitSlot> flipFitSlotMap = new HashMap<Integer,FlipFitSlot>();
    static int gymOwnerIdCounter = 1;
    static int gymIdCounter = 1;
    static int slotIdCounter = 1;

    @Override
    public FlipFitGymOwner registerGymOwner(FlipFitGymOwner gymOwner) {
        gymOwner.setUserId(gymOwnerIdCounter++);
        flipFitGymOwnerMap.put(gymOwner.getUserId(), gymOwner);
        System.out.println(ColorConstants.GREEN + "✅ Gym Owner registered successfully with ID: "
                + gymOwner.getUserId() + ColorConstants.RESET);
        return gymOwner;
    }

    @Override
    public FlipFitGym addGym(FlipFitGym gym) {
        gym.setGymID(gymIdCounter++);
        flipFitGymMap.put(gym.getGymID(), gym);
        System.out.println(ColorConstants.GREEN + "✅ Gym  registered successfully with ID: "
                + gym.getGymID() + ColorConstants.RESET);
        return gym;
    }

    @Override
    public List<FlipFitGym> viewGyms(int gymOwnerId) {
        List<FlipFitGym> filteredGyms = new ArrayList<>();

        for (FlipFitGym gym : flipFitGymMap.values()) {
            if (gym.getGymOwnerID() == gymOwnerId) {
                filteredGyms.add(gym);
            }
        }

        if (filteredGyms.isEmpty()) {
            System.out.println(ColorConstants.RED + "❗ No gyms found for Gym Owner ID: " + gymOwnerId + ColorConstants.RESET);
        }

        return filteredGyms;
    }

    @Override
    public List<FlipFitTransaction> viewTransactions() {
        return List.of();
    }

    @Override
    public FlipFitGymOwner editDetails(FlipFitGymOwner gymOwner) {
        flipFitGymOwnerMap.put(gymOwner.getUserId(), gymOwner);
        return gymOwner;
    }

    @Override
    public FlipFitGymOwner viewDetails(int gymOwnerId) {
        FlipFitGymOwner gymOwner = flipFitGymOwnerMap.get(gymOwnerId);
        if (gymOwner == null) {
            System.out.println(ColorConstants.RED + "❗ Customer ID " + gymOwnerId + " not found." + ColorConstants.RESET);
        }
        return gymOwner;
    }

    @Override
    public boolean deleteGym(int gymId) {
        if(flipFitGymMap.containsKey(gymId)){
            flipFitGymMap.remove(gymId);
            return true;
        }
        return false;
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
    public boolean login(String gymOwnerName, String password) {
        return false;
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
}
