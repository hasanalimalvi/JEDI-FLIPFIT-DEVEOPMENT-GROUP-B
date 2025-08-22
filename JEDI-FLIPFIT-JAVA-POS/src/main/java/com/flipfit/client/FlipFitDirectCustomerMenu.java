package com.flipfit.client;

import com.flipfit.bean.*;
import com.flipfit.business.FlipFitDirectCustomerService;
import com.flipfit.business.FlipFitDirectCustomerServiceImpl;
import com.flipfit.constant.ColorConstants;

import java.time.LocalDate;
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
    ║  1 → 🏋️  View Gyms                         ║
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

                    System.out.println("Enter Date : ");
                    String date = input.next();
                    LocalDate dateObj = LocalDate.parse(date);

                    System.out.println("📅 Viewing slots for Gym ID " + gymId + "...");
                    List<FlipFitSlotAvailability> flipFitSlots = flipFitDirectCustomerService.viewSlots(gymId, dateObj);
                    System.out.println(flipFitSlots);
                    // Call method to view slots by gymId
                }
                case 3 -> {
                    System.out.println("📖 Viewing your bookings...");

                    int userId = FlipFitDirectCustomerServiceImpl.loggedInDirectCustomer.getUserId();

                    List<FlipFitBooking> flipFitBookings = flipFitDirectCustomerService.viewBookedSlots(userId);

                    if (flipFitBookings.isEmpty()) {
                        System.out.println("📭 No bookings found for User ID: " + userId);
                    } else {
                        System.out.println("📋 Your Bookings:");
                        for (FlipFitBooking booking : flipFitBookings) {
                            System.out.println(booking);
                        }
                    }
                }
                case 4 -> {
                    System.out.println("🛎️ Booking a slot...");


                    int userId = FlipFitDirectCustomerServiceImpl.loggedInDirectCustomer.getUserId();

                    System.out.print("🏋️ Enter the Slot ID you want to book:> ");
                    int slotId = input.nextInt();

                    System.out.println("Enter Date in specific format : ");
                    String date = input.next();
                    LocalDate dateObj = LocalDate.parse(date);





                    FlipFitBooking booking = flipFitDirectCustomerService.makeFlipFitBooking(userId, slotId, dateObj);

                    if (booking != null) {
                        System.out.println(booking);
                    } else {
                        System.out.println("⚠️ Booking failed. Please check the slot availability or try again later.");
                    }
                }

                case 5 -> {
                    System.out.println("❌ Cancelling a booking...");
                    System.out.print("Enter Booking ID to cancel: ");

                    Scanner scanner = new Scanner(System.in);
                    int bookingId = scanner.nextInt();

                    boolean bo = flipFitDirectCustomerService.cancelFlipFitBooking(bookingId);
                    if (bo){
                        System.out.println("Booking Cancelled Successfully!!");
                    }

                    else{
                        System.out.println("Booking Cancelled Failed!!");
                    }

                }
                case 6 -> {

                    int id = FlipFitDirectCustomerServiceImpl.loggedInDirectCustomer.getUserId();
                    System.out.println("👁️ Viewing your profile...");
                    System.out.println(flipFitDirectCustomerService.viewDetails(id));
                }
                case 7 -> {
                    System.out.println("✏️ Editing your profile...");

                    Scanner scanner = new Scanner(System.in);


                    int userId = FlipFitDirectCustomerServiceImpl.loggedInDirectCustomer.getUserId();

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
                    customer.setRoleId(1);
                    customer.setPhoneNumber(phoneNumber);
                    customer.setCity(city);
                    customer.setPinCode(pinCode);

                    // Call the editDetails method
                    FlipFitDirectCustomer updatedCustomer = flipFitDirectCustomerService.editDetails(customer);


                }

                case 8 -> {
                    try {
                        Scanner scanner = new Scanner(System.in);

                        System.out.println(ColorConstants.YELLOW + "💳 Redirecting to payment gateway... Please wait." + ColorConstants.RESET);


                        int userId = FlipFitDirectCustomerServiceImpl.loggedInDirectCustomer.getUserId();

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
                    }
                    catch(Exception e){
                        System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
                    }
                }

                case 9 -> {

                    System.out.println(ColorConstants.YELLOW + "🔓 Logging out... Thank you for using FlipFit!" + ColorConstants.RESET);
                    FlipFitDirectCustomerServiceImpl.loggedInDirectCustomer = null;
                }
                default -> {
                    System.out.println(ColorConstants.RED + "❗ Invalid choice. Please select a valid option." + ColorConstants.RESET);
                }
            }
        } while (choice != 9);


    }
}
