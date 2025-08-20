package com.flipfit.client;

import com.flipfit.constant.ColorConstants;

public class FlipFitDirectCustomerMenu {
    public void getDirectCustomerMenu() {
        System.out.println(ColorConstants.CYAN + """
        ╔════════════════════════════════════════════╗
        ║         🧍 GYM CUSTOMER DASHBOARD          ║
        ╠════════════════════════════════════════════╣
        ║  1 → 🏋️  View Gyms by Location             ║
        ║  2 → 📅 View Slots by Gym ID               ║
        ║  3 → 📖 View Bookings                      ║
        ║  4 → 🛎️  Book a Slot                       ║
        ║  5 → ❌ Cancel Booking                     ║
        ║  6 → 👁️  View Profile                      ║
        ║  7 → ✏️  Edit Profile                      ║
        ║  8 → 🔓 Logout                             ║
        ╚════════════════════════════════════════════╝
        """ + ColorConstants.RESET);

    }
}
