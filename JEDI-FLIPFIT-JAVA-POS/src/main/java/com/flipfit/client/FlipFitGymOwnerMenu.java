package com.flipfit.client;

import com.flipfit.bean.FlipFitGym;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.bean.FlipFitSlot;
import com.flipfit.business.FlipFitGymOwnerService;
import com.flipfit.business.FlipFitGymOwnerServiceImpl;
import com.flipfit.constant.ColorConstants;

import java.util.Scanner;

public class FlipFitGymOwnerMenu {

    FlipFitGymOwnerService flipFitGymOwnerService = new FlipFitGymOwnerServiceImpl();

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
            ║  9 ->  🗑️ Delete Gym                       ║
            ║  10 → 📖 View Slots by GymId               ║
            ║  11 → 🔓 Logout                            ║
            ╚════════════════════════════════════════════╝
            """ + ColorConstants.RESET);

            System.out.print(ColorConstants.GREEN + "Enter your choice:> " + ColorConstants.RESET);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("➕ Adding a new gym...");
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("Enter Gym Owner ID: ");
                    int gymOwnerID = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    System.out.print("Enter Gym Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Enter Pin Code: ");
                    String pinCode = scanner.nextLine();

                    System.out.print("Enter Gym Description: ");
                    String description = scanner.nextLine();

                    FlipFitGym newGym = new FlipFitGym();
                    newGym.setGymOwnerID(gymOwnerID);
                    newGym.setAddress(address);
                    newGym.setPinCode(pinCode);
                    newGym.setDescription(description);
                    newGym.setApproved(false);

                    flipFitGymOwnerService.addGym(newGym);

                }
                case 2 -> {

                    System.out.println("Enter your Gym Owner Id : ");
                    int id = input.nextInt();

                    System.out.println("🏋️ Viewing your gyms...");
                    System.out.println(flipFitGymOwnerService.viewGyms(id));
                }
                case 3 -> {
                    System.out.println("➕ Adding a new slot...");
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("🏋️ Enter Gym ID: ");
                    int gymId = scanner.nextInt();

                    System.out.print("⏰ Enter Start Time (e.g., 900 for 9:00 AM): ");
                    int startTime = scanner.nextInt();

                    System.out.print("💺 Enter Total Seats: ");
                    int totalSeats = scanner.nextInt();

                    System.out.print("🪑 Enter Seats Available: ");
                    int seatsAvailable = scanner.nextInt();


                    FlipFitSlot newSlot = new FlipFitSlot();
                    newSlot.setGymId(gymId);
                    newSlot.setStartTime(startTime);
                    newSlot.setTotalSeats(totalSeats);
                    newSlot.setSeatsAvailable(seatsAvailable);


                    flipFitGymOwnerService.addSlot(newSlot);
                    System.out.println("✅ Slot added successfully!");
                    System.out.println(newSlot);

                }
                case 4 -> {
                    System.out.println("🗑️ Deleting a slot...");
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("⏰ Enter Slot ID: ");
                    int slotId = scanner.nextInt();

                    boolean deleted = flipFitGymOwnerService.deleteSlot(slotId);

                    if (deleted) {
                        System.out.println(ColorConstants.GREEN + "✅ Slot deleted successfully!" + ColorConstants.RESET);
                    } else {
                        System.out.println(ColorConstants.RED + "❌ Slot not found or could not be deleted." + ColorConstants.RESET);
                    }

                }
                case 5 -> {
                    System.out.println("📖 Viewing bookings...");

                }
                case 6 -> {

                    System.out.println("Enter Gym Owner Id : ");
                    int id = input.nextInt();

                    System.out.println("👁️ Viewing your profile...");
                    System.out.println(flipFitGymOwnerService.viewDetails(id));

                }
                case 7 -> {
                    System.out.println("✏️ Editing GymOwner profile...");
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("🆔 Enter your GymOwner ID: ");
                    int ownerId = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    FlipFitGymOwner gymOwner = flipFitGymOwnerService.viewDetails(ownerId);

                    if (gymOwner == null) {
                        System.out.println(ColorConstants.RED + "❌ No GymOwner found with ID: " + ownerId + ColorConstants.RESET);
                        break;
                    }

                    System.out.println(ColorConstants.YELLOW + "📝 Leave field blank to keep current value." + ColorConstants.RESET);

                    System.out.print("👤 New Name (" + gymOwner.getUsername() + "): ");
                    String name = scanner.nextLine();
                    if (!name.isBlank()) gymOwner.setUsername(name);

                    System.out.print("📧 New Email (" + gymOwner.getEmail() + "): ");
                    String email = scanner.nextLine();
                    if (!email.isBlank()) gymOwner.setEmail(email);

                    System.out.print("📱 New Phone Number (" + gymOwner.getPhoneNumber() + "): ");
                    String phone = scanner.nextLine();
                    if (!phone.isBlank()) gymOwner.setPhoneNumber(phone);


                    System.out.print("📍 New City (" + gymOwner.getCity() + "): ");
                    String location = scanner.nextLine();
                    if (!location.isBlank()) gymOwner.setCity(location);

                    flipFitGymOwnerService.editDetails(gymOwner);
                    System.out.println(ColorConstants.GREEN + "✅ GymOwner profile updated successfully!" + ColorConstants.RESET);
                    System.out.println(gymOwner);
                }
                case 8 -> {
                    System.out.println("💳 Viewing payments...");

                }
                case 9 -> {
                    System.out.println("💳 Deleting Gym...");

                    System.out.println("Enter Gym Id :");
                    int gymId = input.nextInt();


                    boolean deleted = flipFitGymOwnerService.deleteGym(gymId);

                    if (deleted) {
                        System.out.println(ColorConstants.GREEN + "✅ Gym deleted successfully!" + ColorConstants.RESET);
                    } else {
                        System.out.println(ColorConstants.RED + "❌ Gym not found or could not be deleted." + ColorConstants.RESET);
                    }

                }
                case 10 -> {
                    System.out.println(ColorConstants.YELLOW + "📖 Viewing Slots..." + ColorConstants.RESET);

                    System.out.println("Enter Gym Id : ");
                    int id = input.nextInt();

                    System.out.println(flipFitGymOwnerService.viewSlots(id));
                }
                case 11 -> {
                    System.out.println(ColorConstants.YELLOW + "🔓 Logging out... Stay fit!" + ColorConstants.RESET);
                }
                default -> {
                    System.out.println(ColorConstants.RED + "❗ Invalid choice. Please select a valid option." + ColorConstants.RESET);
                }
            }

        } while (choice != 11);
    }
}
