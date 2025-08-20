package com.flipfit.business;

import com.flipfit.bean.FlipFitGym;
import com.flipfit.bean.FlipFitSlot;

import java.util.List;

public interface GymService {

    boolean deleteGym(int gymId);
    List<FlipFitSlot> viewSlots(int gymId);
    FlipFitGym updateGym(FlipFitGym gym);
}
