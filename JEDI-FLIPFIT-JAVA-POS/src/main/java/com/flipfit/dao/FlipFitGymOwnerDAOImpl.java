package com.flipfit.dao;

import com.flipfit.bean.*;

import java.util.List;

public class FlipFitGymOwnerDAOImpl implements FlipFitGymOwnerDAO{
    @Override
    public FlipFitGymOwner registerGymOwner(FlipFitGymOwner gymOwner) {
        return null;
    }

    @Override
    public FlipFitGym addGym(FlipFitGym gym) {
        return null;
    }

    @Override
    public FlipFitGym updateGym(FlipFitGym gym) {
        return null;
    }

    @Override
    public FlipFitGym viewGym(int gymId) {
        return null;
    }

    @Override
    public List<FlipFitGym> viewGyms(int gymOwnerId) {
        return List.of();
    }

    @Override
    public List<FlipFitTransaction> viewTransactions(int gymId) {
        return List.of();
    }

    @Override
    public FlipFitGymOwner editDetails(FlipFitGymOwner gymOwner) {
        return null;
    }

    @Override
    public FlipFitGymOwner viewDetails(int gymOwnerId) {
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
    public FlipFitGymOwner login(String gymOwnerName, String password) {
        return null;
    }

    @Override
    public FlipFitSlot addSlot(FlipFitSlot slot) {
        return null;
    }

    @Override
    public boolean deleteSlot(int slotId) {
        return false;
    }

    @Override
    public List<FlipFitBooking> viewBookings(int gymId) {
        return List.of();
    }
}
