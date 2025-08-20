package com.flipfit.client;

import com.flipfit.constant.ColorConstants;

public class FlipFitAdminMenu {
    public void getAdminMenu() {
        System.out.println(ColorConstants.PURPLE + """
                ╔════════════════════════════════════════════╗
                ║           🧑‍💼 ADMIN DASHBOARD               ║
                ╠════════════════════════════════════════════╣
                ║  1 → 📨 View Pending Requests              ║
                ║  2 → 🏢 View All Gym Owners                ║
                ║  3 → 🧍 View All Gym Customers             ║
                ║  4 → 🏋️  View All Gyms                     ║
                ║  5 → 🔓 Logout                             ║
                ╚════════════════════════════════════════════╝
                """ + ColorConstants.RESET);
    }
}
