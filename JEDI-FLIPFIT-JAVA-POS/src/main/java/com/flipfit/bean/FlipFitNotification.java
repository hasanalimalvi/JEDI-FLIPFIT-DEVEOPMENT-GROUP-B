package com.flipfit.bean;

import com.flipfit.constant.ColorConstants;

public class FlipFitNotification {
    private int notificationId;
    private int userId;
    private String message;

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "\n" + ColorConstants.PURPLE +
                "╔════════════════════════════════════════════════╗\n" +
                "║           🔔 FLIPFIT NOTIFICATION DETAILS      ║\n" +
                "╠════════════════════════════════════════════════╣\n" +
                String.format("║ 🆔 Notification ID : %-25s ║\n", notificationId) +
                String.format("║ 👤 User ID         : %-25s ║\n", userId) +
                String.format("║ 💬 Message         : %-25s ║\n", message) +
                "╚════════════════════════════════════════════════╝" +
                ColorConstants.RESET;
    }

}
