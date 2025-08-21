package com.flipfit.business;

import com.flipfit.bean.*;

import java.util.List;

public interface FlipFitGymOwnerService {
    FlipFitGymOwner registerGymOwner(FlipFitGymOwner gymOwner);
    FlipFitGym addGym(FlipFitGym gym);
    FlipFitGym updateGym(FlipFitGym gym);
    FlipFitGym viewGym(int gymId);
    List<FlipFitGym> viewGyms(int gymOwnerId);
    List<FlipFitTransaction> viewTransactions(int gymId);
    FlipFitGymOwner editDetails(FlipFitGymOwner gymOwner);
    FlipFitGymOwner viewDetails(int gymOwnerId);

    boolean deleteGym(int gymId);
    List<FlipFitSlot> viewSlots(int gymId);

    FlipFitGymOwner login(String username, String password);

    //Slot
    FlipFitSlot addSlot(FlipFitSlot slot);
    boolean deleteSlot(int slotId);
    List<FlipFitBooking> viewBookings(int gymId);
}
