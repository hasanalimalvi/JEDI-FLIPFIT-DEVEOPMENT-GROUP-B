package com.flipfit.business;

import com.flipfit.bean.FlipFitSlot;

public interface SlotService {
    FlipFitSlot addSlot(FlipFitSlot slot);
    FlipFitSlot getSlotDetails();
    boolean updateAvailability(FlipFitSlot slot);

}
