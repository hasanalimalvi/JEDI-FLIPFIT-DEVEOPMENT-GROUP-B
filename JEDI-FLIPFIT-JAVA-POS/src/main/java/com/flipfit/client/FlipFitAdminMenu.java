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
        ║  1 → 📨 View Pending Gym Owner Requests    ║
        ║  2 → 🏢 View All Gym Owners                ║
        ║  3 → 🧍 View All Gym Customers             ║
        ║  4 → 🏋️  View All Gyms                     ║
        ║  5 → 💳 View Payments                      ║
        ║  6 -> Approve Gym Owner                    ║
        ║  7 -> Approve Gym                          ║ 
        ║  8 -> View Approved Gym Owner Requests     ║
        ║  9 -> View Pending Gym Requests            ║
        ║  10 -> View Approved Gym Requests          ║
        ║  11 → 🔓 Logout                            ║
        ╚════════════════════════════════════════════╝
        """ + ColorConstants.RESET);

            System.out.print(ColorConstants.GREEN + "Enter your choice:> " + ColorConstants.RESET);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("📨 Viewing pending Gym Owner requests...");
                    System.out.println(flipFitAdminService.getPendingGymOwnerList());
                }
                case 2 -> {
                    System.out.println("🏢 Viewing all gym owners...");
                    System.out.println(flipFitAdminService.getGymOwners());
                }
                case 3 -> {
                    System.out.println("🧍 Viewing all gym customers...");
                    System.out.println(flipFitAdminService.getCustomerList());
                }
                case 4 -> {
                    System.out.println("🏋️ Viewing all gyms...");
                    System.out.println(flipFitAdminService.getGyms());
                }
                case 5 -> {
                    System.out.println("💳 Viewing payments...");
                    // Call method to view payments
                }
                case 6 -> {
                    System.out.println("Approve Gym Owner ...");

                    System.out.println("Enter Gym Owner Id : ");
                    int id = input.nextInt();

                    flipFitAdminService.validateGymOwner(id);
                    System.out.println("Gym Owner Approved...");
                }
                case 7 -> {
                    System.out.println("Approve Gym ...");

                    System.out.println("Enter Gym Id : ");
                    int id = input.nextInt();

                    flipFitAdminService.validateGym(id);
                    System.out.println("Gym Approved");
                }
                case 8 -> {
                    System.out.println("View Approved Gym Owners Request List ...");
                    System.out.println(flipFitAdminService.getApprovedGymOwnerList());
                }
                case 9 -> {
                    System.out.println("View Pending Gym Request List ...");
                    System.out.println(flipFitAdminService.getPendingGymList());
                }
                case 10 -> {
                    System.out.println("View Approved Gym Request List ...");
                    System.out.println(flipFitAdminService.getApprovedGymList());
                }
                case 11 -> {
                    System.out.println(ColorConstants.YELLOW + "🔓 Logging out... See you next time!" + ColorConstants.RESET);
                }
                default -> {
                    System.out.println(ColorConstants.RED + "❗ Invalid choice. Please select a valid option." + ColorConstants.RESET);
                }
            }

        } while (choice != 11);

    }
}
