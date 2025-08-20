package com.flipfit.client;

import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.constant.ColorConstants;

import java.util.Scanner;

public class FlipFitApplication {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int flipfitMenuChoice;

        System.out.println("WELCOME TO THE FLIPFIT APPLICATION");

        System.out.println("FLIPFIT MENU");

        System.out.println("""
                        Type:
                         1 -> Login
                         2 -> Registration of Gym Customer
                         3 -> Registration of Gym Owner
                         4 -> Exit
                        """);

        flipfitMenuChoice = input.nextInt();

        switch (flipfitMenuChoice){
            case 1: {

                System.out.println("=========== Login ===========");

                System.out.print("Enter your username:> ");
                String username = input.next();

                System.out.print("Enter your password:> ");
                String password = input.next();

                System.out.print("Enter your role:> GymCustomer/GymAdmin/GymOwner ");
                String role = input.next();

                switch (role){
                    case "GymCustomer": {
                        FlipFitDirectCustomerMenu customerMenu = new FlipFitDirectCustomerMenu();
                        customerMenu.getDirectCustomerMenu();
                        break;
                    }
                    case "GymOwner" : {
                        FlipFitGymOwnerMenu gymOwnerMenu = new FlipFitGymOwnerMenu();
                        gymOwnerMenu.getGymOwnerMenu();
                        break;
                    }
                    case "GymAdmin" : {
                       FlipFitAdminMenu adminMenu = new FlipFitAdminMenu();
                       adminMenu.getAdminMenu();
                       break;
                    }
                    default: {
                        System.out.println("Please Enter Correct role");
                    }
                }

                break;
            }
            case 2 : {

                System.out.println(ColorConstants.BLUE + "=========== Registration of Gym Customer ===========" + ColorConstants.RESET);

                System.out.print(ColorConstants.PURPLE + "Enter username:> " + ColorConstants.RESET);
                String username = input.next();

                System.out.print(ColorConstants.PURPLE + "Enter your email address:> " + ColorConstants.RESET);
                String email = input.next();

                System.out.print(ColorConstants.PURPLE + "Enter your phone number:> " + ColorConstants.RESET);
                String phoneNumber = input.next();

                System.out.print(ColorConstants.PURPLE + "Enter your city:> " + ColorConstants.RESET);
                String city = input.next();

                System.out.print(ColorConstants.PURPLE + "Enter your pin code:> " + ColorConstants.RESET);
                String pinCode = input.next();

                System.out.print(ColorConstants.PURPLE + "Enter your password:> " + ColorConstants.RESET);
                String password = input.next();

                FlipFitDirectCustomer gymCustomer = new FlipFitDirectCustomer();
                gymCustomer.setUsername(username);
                gymCustomer.setEmail(email);
                gymCustomer.setPhoneNumber(phoneNumber);
                gymCustomer.setCity(city);
                gymCustomer.setPinCode(pinCode);
                gymCustomer.setPassword(password);
                gymCustomer.setRoleId(0);

                System.out.println(gymCustomer);
                break;
            }
            case 3 : {
                break;
            }
            case 4 : {
                System.out.println("Thank You for Visiting FlipFit.");
                break;
            }
            default:
                System.out.println("Please type correct choice");
        }
    }
}
