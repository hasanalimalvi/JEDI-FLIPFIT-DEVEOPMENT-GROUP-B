package com.flipfit.client;

import com.flipfit.business.FlipFitAdminService;
import com.flipfit.business.FlipFitAdminServiceImpl;
import com.flipfit.business.FlipFitDirectCustomerService;
import com.flipfit.business.FlipFitDirectCustomerServiceImpl;
import com.flipfit.constant.ColorConstants;

import java.util.Scanner;

public class FlipFitAdminMenu {
    FlipFitAdminService flipFitAdminService = new FlipFitAdminServiceImpl();
    public void getAdminMenu() {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
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

            System.out.print(ColorConstants.GREEN + "Enter your choice:> " + ColorConstants.RESET);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("📨 Viewing pending requests...");
                    // Call method to view pending requests
                }
                case 2 -> {
                    System.out.println("🏢 Viewing all gym owners...");
                    // Call method to view gym owners
                }
                case 3 -> {
                    System.out.println("🧍 Viewing all gym customers...");
                    System.out.println(flipFitAdminService.getCustomerList());
                }
                case 4 -> {
                    System.out.println("🏋️ Viewing all gyms...");
                    // Call method to view gyms
                }
                case 5 -> {
                    System.out.println("👁️ Viewing admin profile...");
                    // Call method to view profile
                }
                case 6 -> {
                    System.out.println("✏️ Editing admin profile...");
                    // Call method to edit profile
                }
                case 7 -> {
                    System.out.println("💳 Viewing payments...");
                    // Call method to view payments
                }
                case 8 -> {
                    System.out.println(ColorConstants.YELLOW + "🔓 Logging out... See you next time!" + ColorConstants.RESET);
                }
                default -> {
                    System.out.println(ColorConstants.RED + "❗ Invalid choice. Please select a valid option." + ColorConstants.RESET);
                }
            }

        } while (choice != 8);

    }
}
