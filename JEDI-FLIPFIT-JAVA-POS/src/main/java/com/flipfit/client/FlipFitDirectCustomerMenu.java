package com.flipfit.client;

import com.flipfit.constant.ColorConstants;

import java.util.Scanner;

public class FlipFitDirectCustomerMenu {
    public void getDirectCustomerMenu() {
        Scanner input = new Scanner(System.in);
        int choice;

        do {
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

            System.out.print(ColorConstants.GREEN + "Enter your choice:> " + ColorConstants.RESET);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("🏋️ Viewing gyms by location...");
                    // Call method to view gyms by location
                }
                case 2 -> {
                    System.out.print("Enter Gym ID:> ");
                    int gymId = input.nextInt();
                    System.out.println("📅 Viewing slots for Gym ID " + gymId + "...");
                    // Call method to view slots by gymId
                }
                case 3 -> {
                    System.out.println("📖 Viewing your bookings...");
                    // Call method to view bookings
                }
                case 4 -> {
                    System.out.println("🛎️ Booking a slot...");
                    // Call method to book a slot
                }
                case 5 -> {
                    System.out.println("❌ Cancelling a booking...");
                    // Call method to cancel booking
                }
                case 6 -> {
                    System.out.println("👁️ Viewing your profile...");
                    // Call method to view profile
                }
                case 7 -> {
                    System.out.println("✏️ Editing your profile...");
                    // Call method to edit profile
                }
                case 8 -> {
                    System.out.println(ColorConstants.YELLOW + "🔓 Logging out... Thank you for using FlipFit!" + ColorConstants.RESET);
                }
                default -> {
                    System.out.println(ColorConstants.RED + "❗ Invalid choice. Please select a valid option." + ColorConstants.RESET);
                }
            }
        } while (choice != 8);


    }
}
