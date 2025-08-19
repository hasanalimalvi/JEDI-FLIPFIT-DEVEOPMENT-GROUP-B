package com.flipfit.business;

import com.flipfit.bean.Slot;

public interface SlotService {
    Slot addSlot(Slot slot);
    Slot getSlotDetails();
    boolean updateAvailability(Slot slot);

}
