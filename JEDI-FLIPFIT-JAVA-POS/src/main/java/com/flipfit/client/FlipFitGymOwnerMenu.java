package com.flipfit.client;

import com.flipfit.constant.ColorConstants;

public class FlipFitGymOwnerMenu {
    public void getGymOwnerMenu() {
        System.out.println(ColorConstants.YELLOW + """
                ╔════════════════════════════════════════════╗
                ║           🏢 GYM OWNER DASHBOARD           ║
                ╠════════════════════════════════════════════╣
                ║  1 → ➕ Add Gym                            ║
                ║  2 → 🏋️  View Gyms                         ║
                ║  3 → ➕ Add Slot                           ║
                ║  4 → 🗑️  Delete Slot                       ║
                ║  5 → 📖 View Bookings                      ║
                ║  6 → 🔓 Logout                             ║
                ╚════════════════════════════════════════════╝
                """ + ColorConstants.RESET);
    }
}

