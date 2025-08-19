package com.flipfit.business;

import com.flipfit.bean.Gym;
import com.flipfit.bean.Slot;

import java.util.List;

public interface GymService {

    boolean deleteGym(int gymId);
    List<Slot> viewSlots(int gymId);
    Gym updateGym(Gym gym);
}
