package com.flipfit.bean;

import com.flipfit.constant.ColorConstants;

import java.time.LocalDate;

public class FlipFitBooking {
    private int bookingId;
    public int userId;
    private int slotId;
    private boolean isCancelled;
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getFlipFitBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "\n" + ColorConstants.YELLOW +
                "╔════════════════════════════════════════════════╗\n" +
                "║             📅 FLIPFIT BOOKING DETAILS         ║\n" +
                "╠════════════════════════════════════════════════╣\n" +
                String.format("║ 🆔 Booking ID    : %-27s ║\n", bookingId) +
                String.format("║ 👤 User ID       : %-27s ║\n", userId) +
                String.format("║ ⏰ Slot ID       : %-27s ║\n", slotId) +
                String.format("║ 📆 Date          : %-27s ║\n", date != null ? date.toString() : "N/A") +
                String.format("║ ❌ Cancelled     : %-27s ║\n", isCancelled ? "Yes" : "No") +
                "╚════════════════════════════════════════════════╝" +
                ColorConstants.RESET;
    }

}
