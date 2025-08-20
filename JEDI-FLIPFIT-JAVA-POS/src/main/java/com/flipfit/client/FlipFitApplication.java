package com.flipfit.client;

import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.constant.ColorConstants;

import java.util.Scanner;

public class FlipFitApplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int flipfitMenuChoice;

        System.out.println(ColorConstants.CYAN + """
                ╔════════════════════════════════════════════╗
                ║        WELCOME TO THE FLIPFIT APP 💪        ║
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
                            FlipFitDirectCustomerMenu customerMenu = new FlipFitDirectCustomerMenu();
                            customerMenu.getDirectCustomerMenu();
                        }
                        case 2 -> {
                            FlipFitGymOwnerMenu gymOwnerMenu = new FlipFitGymOwnerMenu();
                            gymOwnerMenu.getGymOwnerMenu();
                        }
                        case 3 -> {
                            FlipFitAdminMenu adminMenu = new FlipFitAdminMenu();
                            adminMenu.getAdminMenu();
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
                    gymCustomer.setRoleId(0);

                    System.out.println(ColorConstants.GREEN + "\n✅ Registration Successful! Here's your info:" + ColorConstants.RESET);
                    System.out.println(gymCustomer);
                }

                case 3 -> {
                    System.out.println(ColorConstants.BLUE + "\n🏢 REGISTRATION - GYM OWNER 🏢" + ColorConstants.RESET);
                    // You can add similar prompts and formatting here for Gym Owner registration
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