package com.flipfit.business;

import com.flipfit.bean.FlipFitGym;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.FlipFitTransaction;

import java.util.List;

public interface GymOwnerService {
    FlipFitGymOwner registerGymOwner(FlipFitGymOwner gymOwner);
    FlipFitGym addGym(FlipFitGym gym);
    List<FlipFitGym> viewGyms(FlipFitGymOwner gymOwner);
    List<FlipFitTransaction> viewTransactions();
    FlipFitGymOwner editDetails(FlipFitGymOwner gymOwner);

}
