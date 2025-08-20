package com.flipfit.client;

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
