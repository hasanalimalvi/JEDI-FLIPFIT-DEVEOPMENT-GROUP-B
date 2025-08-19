package com.flipfit.business;

public interface GymOwnerService {
    Gym addGym(Gym gym) throws InvalidChoiceException;
    List<Gym> viewGyms(GymOwner gymOwner);
    List<Transaction> viewTransactions();
    GymOwner editDetails(GymOwner gymOwner) throws InvalidChoiceException;
    GymOwner registerGymOwner(GymOwner gymOwner);
    Slot addSlot(Slot slot);
}
