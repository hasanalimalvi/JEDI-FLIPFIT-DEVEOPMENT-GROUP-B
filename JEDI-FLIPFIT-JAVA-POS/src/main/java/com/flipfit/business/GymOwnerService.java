package com.flipfit.business;

import com.flipfit.bean.Gym;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.Slot;
import com.flipfit.bean.Transaction;

import java.util.List;

public interface GymOwnerService {
    GymOwner registerGymOwner(GymOwner gymOwner);
    Gym addGym(Gym gym);
    List<Gym> viewGyms(GymOwner gymOwner);
    List<Transaction> viewTransactions();
    GymOwner editDetails(GymOwner gymOwner);

}
