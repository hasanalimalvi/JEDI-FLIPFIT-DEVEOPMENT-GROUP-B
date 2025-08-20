package com.flipfit.bean;

import com.flipfit.constant.ColorConstants;

public class FlipFitSlot {
    private int slotId;
    private int gymId;
    private int startTime;
    private int seatsAvailable;
    private int totalSeats;

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    @Override
    public String toString() {
        return "\n" + ColorConstants.BLUE +
                "╔════════════════════════════════════════════════╗\n" +
                "║               🕒 FLIPFIT SLOT INFO             ║\n" +
                "╠════════════════════════════════════════════════╣\n" +
                String.format("║ 🆔 Slot ID       : %-27s ║\n", slotId) +
                String.format("║ 🏋️ Gym ID        : %-27s ║\n", gymId) +
                String.format("║ ⏰ Start Time     : %-27s ║\n", startTime) +
                String.format("║ 💺 Seats Avail.  : %-27s ║\n", seatsAvailable) +
                String.format("║ 🧮 Total Seats    : %-27s ║\n", totalSeats) +
                "╚════════════════════════════════════════════════╝" +
                ColorConstants.RESET;
    }

}
