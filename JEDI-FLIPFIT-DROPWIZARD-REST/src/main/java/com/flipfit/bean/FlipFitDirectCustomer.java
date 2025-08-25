package com.flipfit.bean;

import com.flipfit.constant.ColorConstants;

public class FlipFitDirectCustomer extends FlipFitUser {

    private String phoneNumber;
    private String city;
    private String pinCode;

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

    @Override
    public String toString() {
        return "\n" + ColorConstants.CYAN +
                "╔════════════════════════════════════════════════╗\n" +
                "║           💪 FLIPFIT CUSTOMER PROFILE          ║\n" +
                "╠════════════════════════════════════════════════╣\n" +
                String.format("║ 🆔 User ID       : %-27s ║\n", getUserId()) +
                String.format("║ 👤 Username      : %-27s ║\n", getUsername()) +
                String.format("║ 📧 Email         : %-27s ║\n", getEmail()) +
                String.format("║ 🔒 Password      : %-27s ║\n", getPassword()) +
                String.format("║ 🧬 Role ID       : %-27s ║\n", getRoleId()) +
                String.format("║ 📱 Phone Number  : %-27s ║\n", phoneNumber) +
                String.format("║ 🏙️ City          : %-27s ║\n", city) +
                String.format("║ 📮 Pin Code      : %-27s ║\n", pinCode) +
                "╚════════════════════════════════════════════════╝" +
                ColorConstants.RESET;
    }
}
