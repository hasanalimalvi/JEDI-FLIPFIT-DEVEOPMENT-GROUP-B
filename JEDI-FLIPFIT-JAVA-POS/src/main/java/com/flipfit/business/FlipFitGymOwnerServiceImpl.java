package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.constant.ColorConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipFitGymOwnerServiceImpl implements FlipFitGymOwnerService{


    static Map<Integer, FlipFitGymOwner> flipFitDirectCustomerMap = new HashMap<Integer,FlipFitGymOwner>();
    static Map<Integer, FlipFitGym> flipFitGymMap = new HashMap<Integer,FlipFitGym>();
    static int gymOwnerIdCounter = 1;
    static int gymIdCounter = 1;

    @Override
    public FlipFitGymOwner registerGymOwner(FlipFitGymOwner gymOwner) {
        gymOwner.setUserId(gymOwnerIdCounter++);
        flipFitDirectCustomerMap.put(gymOwner.getUserId(), gymOwner);
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
        return null;
    }

    @Override
    public boolean deleteGym(int gymId) {
        return false;
    }

    @Override
    public List<FlipFitSlot> viewSlots(int gymId) {
        return List.of();
    }

    @Override
    public FlipFitGym updateGym(FlipFitGym gym) {
        return null;
    }

    @Override
    public boolean login(String gymOwnerName, String password) {
        return false;
    }

    @Override
    public FlipFitSlot addSlot(FlipFitSlot slot) {
        return null;
    }
}
