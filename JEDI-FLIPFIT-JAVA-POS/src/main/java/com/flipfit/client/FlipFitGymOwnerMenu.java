package com.flipfit.client;

import com.flipfit.business.FlipFitGymOwnerService;
import com.flipfit.business.FlipFitGymOwnerServiceImpl;
import com.flipfit.constant.ColorConstants;

import java.util.Scanner;

public class FlipFitGymOwnerMenu {

    public void getGymOwnerMenu() {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
            System.out.println(ColorConstants.CYAN + """
            ╔════════════════════════════════════════════╗
            ║           🏢 GYM OWNER DASHBOARD           ║
            ╠════════════════════════════════════════════╣
            ║  1 → ➕ Add Gym                            ║
            ║  2 → 🏋️  View Gyms                         ║
            ║  3 → ➕ Add Slot                           ║
            ║  4 → 🗑️  Delete Slot                       ║
            ║  5 → 📖 View Bookings                      ║
            ║  6 → 👁️  View Profile                      ║
            ║  7 → ✏️  Edit Profile                      ║
            ║  8 → 💳 View Payments                      ║
            ║  9 → 🔓 Logout                             ║
            ╚════════════════════════════════════════════╝
            """ + ColorConstants.RESET);

            System.out.print(ColorConstants.GREEN + "Enter your choice:> " + ColorConstants.RESET);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("➕ Adding a new gym...");

                }
                case 2 -> {
                    System.out.println("🏋️ Viewing your gyms...");

                }
                case 3 -> {
                    System.out.println("➕ Adding a new slot...");

                }
                case 4 -> {
                    System.out.println("🗑️ Deleting a slot...");

                }
                case 5 -> {
                    System.out.println("📖 Viewing bookings...");

                }
                case 6 -> {
                    System.out.println("👁️ Viewing your profile...");

                }
                case 7 -> {
                    System.out.println("✏️ Editing your profile...");

                }
                case 8 -> {
                    System.out.println("💳 Viewing payments...");

                }
                case 9 -> {
                    System.out.println(ColorConstants.YELLOW + "🔓 Logging out... Stay fit!" + ColorConstants.RESET);
                }
                default -> {
                    System.out.println(ColorConstants.RED + "❗ Invalid choice. Please select a valid option." + ColorConstants.RESET);
                }
            }

        } while (choice != 9);
    }
}
