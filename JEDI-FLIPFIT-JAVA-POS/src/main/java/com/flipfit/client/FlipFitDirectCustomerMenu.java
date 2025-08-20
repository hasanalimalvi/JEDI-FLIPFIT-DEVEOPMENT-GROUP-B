package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.FlipFitDirectCustomerService;
import com.flipfit.business.FlipFitDirectCustomerServiceImpl;
import com.flipfit.constant.ColorConstants;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class FlipFitDirectCustomerMenu {
    public void getDirectCustomerMenu() {
        Scanner input = new Scanner(System.in);
        FlipFitDirectCustomerService flipFitDirectCustomerService = new FlipFitDirectCustomerServiceImpl();
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
    ║  8 → 💳 Make Payment                       ║
    ║  9 → 🔓 Logout                             ║
    ╚════════════════════════════════════════════╝
    """ + ColorConstants.RESET);

            System.out.print(ColorConstants.GREEN + "Enter your choice:> " + ColorConstants.RESET);
            choice = input.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("🏋️ Viewing gyms by location...");
                    List<FlipFitGym> flipFitGyms = flipFitDirectCustomerService.viewGyms();
                    System.out.println(flipFitGyms);
                }
                case 2 -> {
                    System.out.print("Enter Gym ID:> ");
                    int gymId = input.nextInt();
                    System.out.println("📅 Viewing slots for Gym ID " + gymId + "...");
                    List<FlipFitSlot> flipFitSlots = flipFitDirectCustomerService.viewSlots(gymId);
                    System.out.println(flipFitSlots);
                    // Call method to view slots by gymId
                }
                case 3 -> {
                    System.out.println("📖 Viewing your bookings...");

                    System.out.print("👤 Enter your User ID:> ");
                    int userId = input.nextInt();

                    List<FlipFitBooking> flipFitBookings = flipFitDirectCustomerService.viewBookedSlots(userId);

                    if (flipFitBookings.isEmpty()) {
                        System.out.println("📭 No bookings found for User ID: " + userId);
                    } else {
                        System.out.println("📋 Your Bookings:");
                        for (FlipFitBooking booking : flipFitBookings) {
                            System.out.println("""
                ----------------------------------------
                📌 Booking ID : %d
                🏋️ Slot ID    : %d
                👤 User ID    : %d
                ❌ Cancelled  : %s
                ----------------------------------------
                """.formatted(
                                    booking.getFlipFitBookingId(),
                                    booking.getSlotId(),
                                    booking.getUserId(),
                                    booking.isCancelled() ? "Yes" : "No"
                            ));
                        }
                    }
                }
                case 4 -> {
                    System.out.println("🛎️ Booking a slot...");

                    System.out.print("👤 Enter your User ID:> ");
                    int userId = input.nextInt();

                    System.out.print("🏋️ Enter the Slot ID you want to book:> ");
                    int slotId = input.nextInt();





                    FlipFitBooking booking = flipFitDirectCustomerService.makeFlipFitBooking(userId, slotId);

                    if (booking != null) {
                        System.out.println("""
                        ✅ Booking Confirmed!
                        📌 Booking ID : %d
                        👤 User ID    : %d
                        🏋️ Slot ID    : %d
                        ❌ Cancelled  : %s
                        """.formatted(
                                booking.getBookingId(),
                                booking.getUserId(),
                                booking.getSlotId(),
                                booking.isCancelled() ? "Yes" : "No"
                        ));
                    } else {
                        System.out.println("⚠️ Booking failed. Please check the slot availability or try again later.");
                    }
                }

                case 5 -> {
                    System.out.println("❌ Cancelling a booking...");
                    System.out.print("Enter Booking ID to cancel: ");

                    Scanner scanner = new Scanner(System.in);
                    int bookingId = scanner.nextInt();

                    flipFitDirectCustomerService.cancelFlipFitBooking(bookingId);
                }
                case 6 -> {
                    System.out.println("Enter your User Id : ");
                    int id = input.nextInt();
                    System.out.println("👁️ Viewing your profile...");
                    System.out.println(flipFitDirectCustomerService.viewDetails(id));
                }
                case 7 -> {
                    System.out.println("✏️ Editing your profile...");

                    Scanner scanner = new Scanner(System.in);

                    System.out.print("Enter User ID: ");
                    int userId = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Enter City: ");
                    String city = scanner.nextLine();

                    System.out.print("Enter Pin Code: ");
                    String pinCode = scanner.nextLine();

                    // Create and populate the customer object
                    FlipFitDirectCustomer customer = new FlipFitDirectCustomer();
                    customer.setUserId(userId);
                    customer.setUsername(username);
                    customer.setEmail(email);
                    customer.setPassword(password);
                    customer.setRoleId(0);
                    customer.setPhoneNumber(phoneNumber);
                    customer.setCity(city);
                    customer.setPinCode(pinCode);

                    // Call the editDetails method
                    FlipFitDirectCustomer updatedCustomer = flipFitDirectCustomerService.editDetails(customer);

                    System.out.println("✅ Profile updated successfully!");
                }

                case 8 -> {
                    Scanner scanner = new Scanner(System.in);

                    System.out.println(ColorConstants.YELLOW + "💳 Redirecting to payment gateway... Please wait." + ColorConstants.RESET);

                    System.out.print("👤 Enter User ID: ");
                    int userId = scanner.nextInt();

                    System.out.print("📌 Enter Booking ID: ");
                    int bookingId = scanner.nextInt();

                    System.out.print("💰 Enter Payment Type (e.g., 1 for UPI, 2 for Card, etc.): ");
                    int paymentType = scanner.nextInt();

                    System.out.print("💵 Enter Amount: ₹");
                    double amount = scanner.nextDouble();

                    FlipFitTransaction transaction = new FlipFitTransaction();
                    transaction.setUserId(userId);
                    transaction.setBookingId(bookingId);
                    transaction.setPaymentType(paymentType);
                    transaction.setAmount(amount);

                    transaction = flipFitDirectCustomerService.makePayment(transaction);
                    System.out.println(ColorConstants.GREEN + "✅ Payment details captured successfully!" + ColorConstants.RESET);
                    System.out.println(transaction); // Assuming toString() is properly overridden
                    break;
                }

                case 9 -> {
                    System.out.println(ColorConstants.YELLOW + "🔓 Logging out... Thank you for using FlipFit!" + ColorConstants.RESET);
                }
                default -> {
                    System.out.println(ColorConstants.RED + "❗ Invalid choice. Please select a valid option." + ColorConstants.RESET);
                }
            }
        } while (choice != 9);


    }
}
