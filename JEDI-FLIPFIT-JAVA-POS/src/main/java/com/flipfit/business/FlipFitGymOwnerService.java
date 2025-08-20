package com.flipfit.business;

import com.flipfit.bean.FlipFitGym;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.FlipFitSlot;
import com.flipfit.bean.FlipFitTransaction;

import java.util.List;

public interface FlipFitGymOwnerService {
    FlipFitGymOwner registerGymOwner(FlipFitGymOwner gymOwner);
    FlipFitGym addGym(FlipFitGym gym);
    List<FlipFitGym> viewGyms(int gymId);
    List<FlipFitTransaction> viewTransactions();
    FlipFitGymOwner editDetails(FlipFitGymOwner gymOwner);

    boolean deleteGym(int gymId);
    List<FlipFitSlot> viewSlots(int gymId);
    FlipFitGym updateGym(FlipFitGym gym);

    boolean login(String gymOwnerName, String password);

    //Slot
    FlipFitSlot addSlot(FlipFitSlot slot);
}
