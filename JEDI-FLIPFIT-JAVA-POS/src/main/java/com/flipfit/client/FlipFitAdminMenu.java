package com.flipfit.client;

import com.flipfit.constant.ColorConstants;

public class FlipFitAdminMenu {
    public void getAdminMenu() {
        System.out.println(ColorConstants.CYAN + """
        ╔════════════════════════════════════════════╗
        ║           🧑‍💼 ADMIN DASHBOARD               ║
        ╠════════════════════════════════════════════╣
        ║  1 → 📨 View Pending Requests              ║
        ║  2 → 🏢 View All Gym Owners                ║
        ║  3 → 🧍 View All Gym Customers             ║
        ║  4 → 🏋️  View All Gyms                     ║
        ║  5 → 👁️  View Profile                      ║
        ║  6 → ✏️  Edit Profile                      ║
        ║  7 → 💳 View Payments                      ║
        ║  8 → 🔓 Logout                             ║
        ╚════════════════════════════════════════════╝
        """ + ColorConstants.RESET);
    }
}
