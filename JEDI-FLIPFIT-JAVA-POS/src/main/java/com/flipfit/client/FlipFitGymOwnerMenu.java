package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.FlipFitGymOwnerService;
import com.flipfit.business.FlipFitGymOwnerServiceImpl;
import com.flipfit.constant.ColorConstants;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
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
        ║  5 → 📖 View Bookings by GymId             ║
        ║  6 → 👁️  View Profile                      ║
        ║  7 → ✏️  Edit Profile                      ║
        ║  8 → 💳 View Payments by GymId             ║ 
        ║  9 → 🗑️ Delete Gym                         ║
        ║ 10 → 📖 View Slots by GymId                ║
        ║ 11 → 🔓 Logout                             ║
        ╚════════════════════════════════════════════╝
        """ + ColorConstants.RESET);

            System.out.print(ColorConstants.GREEN + "Enter your choice:> " + ColorConstants.RESET);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("➕ Adding a new gym...");
                    Scanner scanner = new Scanner(System.in);

                    int gymOwnerID = FlipFitGymOwnerServiceImpl.loggedInGymOwner.getUserId() ;

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
                    int id = FlipFitGymOwnerServiceImpl.loggedInGymOwner.getUserId() ;

                    System.out.println("🏋️ Viewing your gyms...");
                    System.out.println(flipFitGymOwnerService.viewGyms(id));
                }
                case 3 -> {
                    System.out.println("➕ Adding a new slot...");
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("🏋️ Enter Gym ID: ");
                    int gymId = scanner.nextInt();

                    System.out.print("⏰ Enter Start Time (HH:mm format, e.g., 09:00 or 14:30): ");
                    String timeInput = scanner.next();
                    LocalTime startTime;

                    try {
                        startTime = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));
                    } catch (DateTimeParseException e) {
                        System.out.println("❌ Invalid time format. Please use HH:mm (e.g., 09:00).");
                        return;
                    }

                    System.out.print("💺 Enter Total Seats: ");
                    int totalSeats = scanner.nextInt();


                    FlipFitSlot newSlot = new FlipFitSlot();
                    newSlot.setGymId(gymId);
                    newSlot.setStartTime(startTime);
                    newSlot.setTotalSeats(totalSeats);

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
                    Scanner scanner = new Scanner(System.in);

                    System.out.print("Enter your Gym ID: ");
                    int gymId = scanner.nextInt();1

                    List<FlipFitBooking> bookings = flipFitGymOwnerService.viewBookings(gymId);

                    if (bookings.isEmpty()) {
                        System.out.println(ColorConstants.RED + "⚠️ No active bookings found for Gym ID: " + gymId + ColorConstants.RESET);
                    } else {
                        System.out.println(ColorConstants.CYAN + "\n📖 Active Bookings for Gym ID: " + gymId + ColorConstants.RESET);
                        for (FlipFitBooking booking : bookings) {
                            System.out.println(booking);
                        }
                    }

                }

                case 6 -> {

                    System.out.println("Enter Gym Owner Id : ");
                    int id = FlipFitGymOwnerServiceImpl.loggedInGymOwner.getUserId();

                    System.out.println("👁️ Viewing your profile...");
                    System.out.println(flipFitGymOwnerService.viewDetails(id));

                }
                case 7 -> {
                    System.out.println("✏️ Editing GymOwner profile...");
                    Scanner scanner = new Scanner(System.in);

                    int ownerId = FlipFitGymOwnerServiceImpl.loggedInGymOwner.getUserId();

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
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("🏋️ Enter Gym ID to view payments: ");
                    int gymId = scanner.nextInt();

                    System.out.println(ColorConstants.YELLOW + "💳 Fetching payments for Gym ID: " + gymId + "..." + ColorConstants.RESET);

                    List<FlipFitTransaction> transactions = flipFitGymOwnerService.viewTransactions(gymId);

                    if (transactions.isEmpty()) {
                        System.out.println(ColorConstants.RED + "⚠️ No payments found for Gym ID: " + gymId + ColorConstants.RESET);
                    } else {
                        for (FlipFitTransaction txn : transactions) {
                            System.out.println(ColorConstants.CYAN +
                                    "╔════════════════════════════════════════════════╗\n" +
                                    String.format("║ 🆔 Transaction ID : %-26d ║\n", txn.getTransactionId()) +
                                    String.format("║ 👤 User ID        : %-26d ║\n", txn.getUserId()) +
                                    String.format("║ 📌 Booking ID     : %-26d ║\n", txn.getBookingId()) +
                                    String.format("║ 💰 Payment Type   : %-26d ║\n", txn.getPaymentType()) +
                                    String.format("║ 💵 Amount         : ₹%-25.2f ║\n", txn.getAmount()) +
                                    "╚════════════════════════════════════════════════╝" +
                                    ColorConstants.RESET);
                        }
                    }


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

                    System.out.println("Enter Date in specific format : ");
                    String date = input.next();
                    LocalDate dateObj = LocalDate.parse(date);


                    System.out.println(flipFitGymOwnerService.viewSlots(id, dateObj));
                }
                case 11 -> {
                    System.out.println(ColorConstants.YELLOW + "🔓 Logging out... Stay fit!" + ColorConstants.RESET);
                    FlipFitGymOwnerServiceImpl.loggedInGymOwner = null;
                }
                default -> {
                    System.out.println(ColorConstants.RED + "❗ Invalid choice. Please select a valid option." + ColorConstants.RESET);
                }
            }

        } while (choice != 11);
    }
}
