package com.flipfit.bean;

public class Gym {
    private int gymID;
    private int gymOwnerID;
    private String address;
    private String pincode;
    private boolean approved;
    private String description;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
