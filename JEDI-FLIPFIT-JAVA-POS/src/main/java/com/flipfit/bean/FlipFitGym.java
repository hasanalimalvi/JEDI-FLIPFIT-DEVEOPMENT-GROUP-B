package com.flipfit.bean;

import com.flipfit.constant.ColorConstants;

public class FlipFitGym {
    private int gymID;
    private int gymOwnerID;
    private String address;
    private String pinCode;
    private boolean isApproved;
    private String description;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGymID() {
        return gymID;
    }

    public void setGymID(int gymID) {
        this.gymID = gymID;
    }

    public int getGymOwnerID() {
        return gymOwnerID;
    }

    public void setGymOwnerID(int gymOwnerID) {
        this.gymOwnerID = gymOwnerID;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "\n" + ColorConstants.GREEN +
                "╔════════════════════════════════════════════════╗\n" +
                "║              🏋️ FLIPFIT GYM PROFILE           ║\n" +
                "╠════════════════════════════════════════════════╣\n" +
                String.format("║ 🆔 Gym ID        : %-27s ║\n", gymID) +
                String.format("║ 👤 Owner ID      : %-27s ║\n", gymOwnerID) +
                String.format("║ 📍 Address       : %-27s ║\n", address) +
                String.format("║ 📮 Pin Code      : %-27s ║\n", pinCode) +
                String.format("║ ✅ Approved      : %-27s ║\n", isApproved ? "Yes" : "No") +
                String.format("║ 📝 Description   : %-27s ║\n", description) +
                "╚════════════════════════════════════════════════╝" +
                ColorConstants.RESET;
    }

}
