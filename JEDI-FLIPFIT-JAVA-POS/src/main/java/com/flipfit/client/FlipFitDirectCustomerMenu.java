package com.flipfit.client;

public class FlipFitDirectCustomerMenu {
    public void getDirectCustomerMenu(){
        System.out.println( """
                        Choose an option:
                         1. View Gyms
                         2. View Slots by GymId
                         3. View Bookings
                         4. Book a Slot
                         5. Cancel Booking
                         6. Logout
                        """ );
    }
}
