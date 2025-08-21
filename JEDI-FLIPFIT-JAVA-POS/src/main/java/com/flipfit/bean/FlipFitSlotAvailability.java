package com.flipfit.bean;

import com.flipfit.constant.ColorConstants;

import java.time.LocalDate;

public class FlipFitSlotAvailability extends FlipFitSlot {
    private int seatsAvailable;
    private LocalDate date;

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "\n" + ColorConstants.BLUE +
                "╔════════════════════════════════════════════════════════════╗\n" +
                "║                  🕒 FLIPFIT SLOT AVAILABILITY              ║\n" +
                "╠════════════════════════════════════════════════════════════╣\n" +
                String.format("║ 🆔 Slot ID         : %-36s  ║\n", getSlotId()) +
                String.format("║ 🏋️ Gym ID          : %-36s  ║\n", getGymId()) +
                String.format("║ ⏰ Start Time       : %-36s ║\n", getStartTime()) +
                String.format("║ 🧮 Total Seats      : %-36s ║\n", getTotalSeats()) +
                String.format("║ ✅ Seats Available  : %-36s ║\n", seatsAvailable) +
                String.format("║ 📅 Date             : %-36s ║\n", date) +
                "╚════════════════════════════════════════════════════════════╝" +
                ColorConstants.RESET;
    }

}
