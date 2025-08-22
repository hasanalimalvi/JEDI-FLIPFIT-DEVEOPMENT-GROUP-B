package com.flipfit.client;

import com.flipfit.bean.FlipFitAdmin;
import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.business.*;
import com.flipfit.constant.ColorConstants;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.UsernameExistsException;

import java.util.Scanner;

public class FlipFitApplication {
    public static void main(String[] args) throws EntityNotFoundException {
        FlipFitDirectCustomerService flipFitDirectCustomerService = new FlipFitDirectCustomerServiceImpl();
        FlipFitGymOwnerService flipFitGymOwnerService = new FlipFitGymOwnerServiceImpl();
        FlipFitAdminService flipFitAdminService = new FlipFitAdminServiceImpl();
        Scanner input = new Scanner(System.in);
        int flipfitMenuChoice;

        System.out.println(ColorConstants.CYAN + """
                ╔════════════════════════════════════════════╗
                ║        WELCOME TO THE FLIPFIT APP 💪       ║
                ╚════════════════════════════════════════════╝
                """ + ColorConstants.RESET);

        do {
            System.out.println(ColorConstants.YELLOW + """
                    ╔════════════════════════════════════════════╗
                    ║                 FLIPFIT MENU               ║
                    ╠════════════════════════════════════════════╣
                    ║  1 → 🔐 Login                              ║
                    ║  2 → 📝 Register as Gym Customer           ║
                    ║  3 → 🏢 Register as Gym Owner              ║
                    ║  4 → ❌ Exit                               ║
                    ╚════════════════════════════════════════════╝
                    """ + ColorConstants.RESET);

            System.out.print(ColorConstants.GREEN + "Enter your choice:> " + ColorConstants.RESET);
            flipfitMenuChoice = input.nextInt();

            switch (flipfitMenuChoice) {
                case 1 -> {
                    System.out.println(ColorConstants.BLUE + "\n🔐 LOGIN 🔐" + ColorConstants.RESET);

                    System.out.print("👤 Username:> ");
                    String username = input.next();

                    System.out.print("🔑 Password:> ");
                    String password = input.next();





                    System.out.println(ColorConstants.YELLOW + """
                            ╔════════════════════════════════════════════╗
                            ║              SELECT YOUR ROLE              ║
                            ╠════════════════════════════════════════════╣
                            ║  1 → 🧍 Gym Customer                       ║
                            ║  2 → 🏢 Gym Owner                          ║
                            ║  3 → 🧑‍💼 Gym Admin                          ║
                            ╚════════════════════════════════════════════╝
                            """ + ColorConstants.RESET);

                    System.out.print(ColorConstants.GREEN + "Enter your role number:> " + ColorConstants.RESET);
                    int roleChoice = input.nextInt();

                    switch (roleChoice) {
                        case 1 -> {

                            FlipFitDirectCustomer loggedInCustomer = flipFitDirectCustomerService.login(username, password);

                            if (loggedInCustomer != null) {
                                System.out.println(ColorConstants.GREEN + "✅ Welcome, " + loggedInCustomer.getUsername() + "! You have successfully logged in." + ColorConstants.RESET);

                                FlipFitDirectCustomerMenu customerMenu = new FlipFitDirectCustomerMenu();
                                customerMenu.getDirectCustomerMenu();
                            } else {
                                System.out.println(ColorConstants.RED + "❌ Login failed. Returning to main menu..." + ColorConstants.RESET);
                                // Optionally, call main menu again or just let control return
                            }
                        }
                        case 2 -> {
                            FlipFitGymOwner loggedInOwner = flipFitGymOwnerService.login(username, password);

                            if (loggedInOwner != null) {
                                System.out.println(ColorConstants.GREEN + "✅ Welcome, " + loggedInOwner.getUsername() + "! You have successfully logged in." + ColorConstants.RESET);

                                FlipFitGymOwnerMenu gymOwnerMenu = new FlipFitGymOwnerMenu();
                                gymOwnerMenu.getGymOwnerMenu();
                            } else {
                                System.out.println(ColorConstants.RED + "❌ Login failed. Returning to main menu..." + ColorConstants.RESET);
                            }
                        }
                        case 3 -> {
                            FlipFitAdmin loggedInAdmin = flipFitAdminService.login(username, password);

                            if (loggedInAdmin != null) {
                                System.out.println(ColorConstants.GREEN + "✅ Welcome, " + loggedInAdmin.getUsername() + "! You have successfully logged in." + ColorConstants.RESET);

                                FlipFitAdminMenu adminMenu = new FlipFitAdminMenu();
                                adminMenu.getAdminMenu();
                            } else {
                                System.out.println(ColorConstants.RED + "❌ Login failed. Returning to main menu..." + ColorConstants.RESET);
                            }
                        }
                        default ->
                                System.out.println(ColorConstants.RED + "❗ Invalid role number. Please select 1, 2, or 3." + ColorConstants.RESET);
                    }
                }

                case 2 -> {
                    System.out.println(ColorConstants.BLUE + "\n📝 REGISTRATION - GYM CUSTOMER 📝" + ColorConstants.RESET);

                    System.out.print(ColorConstants.PURPLE + "👤 Username:> " + ColorConstants.RESET);
                    String username = input.next();

                    System.out.print(ColorConstants.PURPLE + "📧 Email:> " + ColorConstants.RESET);
                    String email = input.next();

                    System.out.print(ColorConstants.PURPLE + "📱 Phone Number:> " + ColorConstants.RESET);
                    String phoneNumber = input.next();

                    System.out.print(ColorConstants.PURPLE + "🏙️ City:> " + ColorConstants.RESET);
                    String city = input.next();

                    System.out.print(ColorConstants.PURPLE + "📮 Pin Code:> " + ColorConstants.RESET);
                    String pinCode = input.next();

                    System.out.print(ColorConstants.PURPLE + "🔑 Password:> " + ColorConstants.RESET);
                    String password = input.next();

                    FlipFitDirectCustomer gymCustomer = new FlipFitDirectCustomer();
                    gymCustomer.setUsername(username);
                    gymCustomer.setEmail(email);
                    gymCustomer.setPhoneNumber(phoneNumber);
                    gymCustomer.setCity(city);
                    gymCustomer.setPinCode(pinCode);
                    gymCustomer.setPassword(password);
                    gymCustomer.setRoleId(1);

                    try {
                        flipFitDirectCustomerService.registerCustomer(gymCustomer);
                        System.out.println(ColorConstants.GREEN + "\n✅ Registration Successful! Here's your info:" + ColorConstants.RESET);
                        System.out.println(gymCustomer);
                    } catch (Exception e) {
                        System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
                    }
                }

                case 3 -> {
                    System.out.println(ColorConstants.BLUE + "=========== Registration of Gym Owner ===========" + ColorConstants.RESET);

                    System.out.print(ColorConstants.PURPLE + "Enter username:> " + ColorConstants.RESET);
                    String username = input.next();

                    System.out.print(ColorConstants.PURPLE + "Enter your password:> " + ColorConstants.RESET);
                    String password = input.next();

                    System.out.print(ColorConstants.PURPLE + "Enter your email address:> " + ColorConstants.RESET);
                    String email = input.next();

                    System.out.print(ColorConstants.PURPLE + "Enter your phone number:> " + ColorConstants.RESET);
                    String phoneNumber = input.next();

                    System.out.print(ColorConstants.PURPLE + "Enter your city:> " + ColorConstants.RESET);
                    String city = input.next();

                    System.out.print(ColorConstants.PURPLE + "Enter your pin code:> " + ColorConstants.RESET);
                    String pinCode = input.next();

                    System.out.print(ColorConstants.PURPLE + "Enter your panCard:> " + ColorConstants.RESET);
                    String panCard = input.next();

                    System.out.print(ColorConstants.PURPLE + "Enter your gstin:> " + ColorConstants.RESET);
                    String gstin = input.next();

                    System.out.print(ColorConstants.PURPLE + "Enter your aadharNumber:> " + ColorConstants.RESET);
                    String aadharNumber = input.next();

                    FlipFitGymOwner gymOwner = new FlipFitGymOwner();
                    gymOwner.setEmail(email);
                    gymOwner.setPassword(password);
                    gymOwner.setPhoneNumber(phoneNumber);
                    gymOwner.setCity(city);
                    gymOwner.setPinCode(pinCode);
                    gymOwner.setUsername(username);
                    gymOwner.setRoleId(2);
                    gymOwner.setGstin(gstin);
                    gymOwner.setAadharNumber(aadharNumber);
                    gymOwner.setPanCard(panCard);
                    gymOwner.setIsApproved(false);

                  try {
                      flipFitGymOwnerService.registerGymOwner(gymOwner);
                      System.out.println(ColorConstants.GREEN + "\n✅ Registration Successful! Here's your info:" + ColorConstants.RESET);
                      System.out.println(gymOwner);
                  }
                  catch (Exception e) {
                      System.out.println(ColorConstants.RED + e.getMessage() + ColorConstants.RESET);
                  }

                }

                case 4 -> {
                    System.out.println(ColorConstants.YELLOW + "\n👋 Thank you for visiting FlipFit. Stay strong!" + ColorConstants.RESET);
                }

                default ->
                        System.out.println(ColorConstants.RED + "❗ Invalid choice. Please select a valid option." + ColorConstants.RESET);
            }

        } while(flipfitMenuChoice != 4);
    }
}