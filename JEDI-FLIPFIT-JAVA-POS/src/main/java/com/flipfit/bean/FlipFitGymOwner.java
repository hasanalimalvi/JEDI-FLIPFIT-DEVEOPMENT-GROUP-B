package com.flipfit.bean;

public class FlipFitGymOwner extends FlipFitUser {

    private String phoneNumber;
    private String city;
    private String pinCode;
    public String panCard;
    public String gstin;
    public String aadharNumber;
    public boolean isApproved;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPanCard() {
        return panCard;
    }

    public void setPanCard(String panCard) {
        this.panCard = panCard;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean approved) {
        isApproved = approved;
    }


    @Override
    public String toString() {
        return "\n" +
                "╔════════════════════════════════════════════╗\n" +
                "║         🏢 GYM OWNER PROFILE DETAILS       ║\n" +
                "╠════════════════════════════════════════════╣\n" +
                String.format("║ 🆔 User ID       : %-23s ║\n", getUserId()) +
                String.format("║ 👤 Username      : %-23s ║\n", getUsername()) +
                String.format("║ 📧 Email         : %-23s ║\n", getEmail()) +
                String.format("║ 🔒 Password      : %-23s ║\n", getPassword()) +
                String.format("║ 🧬 Role ID       : %-23s ║\n", getRoleId()) +
                String.format("║ 📱 Phone Number  : %-23s ║\n", phoneNumber) +
                String.format("║ 🏙️ City          : %-23s ║\n", city) +
                String.format("║ 📮 Pin Code      : %-23s ║\n", pinCode) +
                String.format("║ 🧾 PAN Card      : %-23s ║\n", panCard) +
                String.format("║ 🧾 GSTIN         : %-23s ║\n", gstin) +
                String.format("║ 🆔 Aadhar Number : %-23s ║\n", aadharNumber) +
                String.format("║ ✅ Approved      : %-23s ║\n", isApproved ? "Yes" : "No") +
                "╚════════════════════════════════════════════╝";
    }


}
