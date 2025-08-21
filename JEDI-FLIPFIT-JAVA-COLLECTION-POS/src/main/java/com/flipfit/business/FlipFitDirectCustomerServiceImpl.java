package com.flipfit.business;

import com.flipfit.bean.*;
import com.flipfit.constant.ColorConstants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipFitDirectCustomerServiceImpl implements FlipFitDirectCustomerService{

    static Map<Integer, FlipFitDirectCustomer> flipFitDirectCustomerMap = new HashMap<Integer,FlipFitDirectCustomer>();
    static int customerIdCounter = 1;
    static Map<Integer, FlipFitBooking> bookingMap = new HashMap<Integer, FlipFitBooking>();
    static int bookingIdCounter = 1;
    static Map<Integer, FlipFitTransaction> transactionMap = new HashMap<Integer, FlipFitTransaction>();
    static int transactionIdCounter = 1;

    static Map<Integer, FlipFitUser> userMap = new HashMap<Integer, FlipFitUser>();
    static int userIdCounter = 1;



    FlipFitGymOwnerService flipFitGymOwnerService = new FlipFitGymOwnerServiceImpl();

    @Override
    public List<FlipFitSlot> viewSlots(int gymId) {
        return flipFitGymOwnerService.viewSlots(gymId);
    }

    @Override
    public List<FlipFitBooking> viewBookedSlots(int userId) {
        List<FlipFitBooking> userBookings = new ArrayList<>();

        for (FlipFitBooking booking : bookingMap.values()) {
            if (booking.getUserId() == userId && !booking.isCancelled()) {
                userBookings.add(booking);
            }
        }

        return userBookings;
    }

    @Override
    public FlipFitDirectCustomer viewDetails(int customerId) {
        FlipFitDirectCustomer customer = flipFitDirectCustomerMap.get(customerId);
        if (customer == null) {
            System.out.println(ColorConstants.RED + "❗ Customer ID " + customerId + " not found." + ColorConstants.RESET);
        }
        return customer;
    }


    @Override
    public FlipFitDirectCustomer registerCustomer(FlipFitDirectCustomer directCustomer) {
        // Assign customer ID
        directCustomer.setUserId(customerIdCounter++);
        flipFitDirectCustomerMap.put(directCustomer.getUserId(), directCustomer);




        System.out.println(ColorConstants.GREEN + "✅ Customer registered successfully with ID: " + directCustomer.getUserId() + ColorConstants.RESET);

        return directCustomer;
    }


    @Override
    public FlipFitDirectCustomer editDetails(FlipFitDirectCustomer directCustomer) {
        if (directCustomer == null) {
            System.out.println("❌ Cannot update: Customer details are null.");
            return null;
        }

        int customerId = directCustomer.getUserId();

        // Check if the user exists in the map
        if (!flipFitDirectCustomerMap.containsKey(customerId)) {
            System.out.println("❌ Customer with ID " + customerId + " not found. Cannot update profile.");
            return null;
        }

        // If the customer exists, proceed with the update
        flipFitDirectCustomerMap.put(customerId, directCustomer);
        System.out.println("✅ Customer details updated successfully for ID: " + customerId);

        return directCustomer;
    }

    @Override
    public List<FlipFitGym> viewGyms() {
        return new ArrayList<>(FlipFitGymOwnerServiceImpl.flipFitGymMap.values());
    }

    @Override
    public FlipFitTransaction makePayment(FlipFitTransaction flipFitTransaction) {
        flipFitTransaction.setTransactionId(transactionIdCounter++);

        transactionMap.put(flipFitTransaction.getTransactionId(), flipFitTransaction);
        return flipFitTransaction;
    }

    @Override
    public FlipFitDirectCustomer login(String customerName, String password) {
        for (FlipFitDirectCustomer customer : flipFitDirectCustomerMap.values()) {
            if (customer.getUsername().equals(customerName) &&
                    customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public FlipFitBooking makeFlipFitBooking(int customerID, int slotId) {
        // Step 1: Fetch the slot
        FlipFitSlot newSlot = FlipFitGymOwnerServiceImpl.flipFitSlotMap.get(slotId);

        // Step 2: Validate slot existence
        if (newSlot == null) {
            System.out.println("❌ Booking failed: Slot ID " + slotId + " does not exist.");
            return null;
        }

        // Step 3: Check seat availability
        if (newSlot.getSeatsAvailable() <= 0) {
            System.out.println("⚠️ Booking failed: No seats available for Slot ID " + slotId +
                    " at Gym ID " + newSlot.getGymId() + " starting at " + newSlot.getStartTime() + ".");
            return null;
        }

        // 🔍 Check for conflicting booking
        for (FlipFitBooking existingBooking : bookingMap.values()) {
            if (!existingBooking.isCancelled() && existingBooking.getUserId() == customerID) {
                FlipFitSlot bookedSlot = FlipFitGymOwnerServiceImpl.flipFitSlotMap.get(existingBooking.getSlotId());
                if (bookedSlot != null && bookedSlot.getStartTime().equals(newSlot.getStartTime())) {
                    // 🛑 Cancel the conflicting booking
                    existingBooking.setCancelled(true);
                    bookedSlot.setSeatsAvailable(bookedSlot.getSeatsAvailable() + 1);
                    System.out.println("⚠️ Existing booking ID " + existingBooking.getBookingId() +
                            " at the same time was cancelled to avoid conflict.");
                    FlipFitGymOwnerServiceImpl.flipFitSlotMap.put(bookedSlot.getSlotId(), bookedSlot);
                }
            }
        }

        // Step 5: Decrease available seats
        newSlot.setSeatsAvailable(newSlot.getSeatsAvailable() - 1);

        // Step 6: Create new booking
        FlipFitBooking booking = new FlipFitBooking();
        booking.setBookingId(bookingIdCounter++);
        booking.setUserId(customerID);
        booking.setSlotId(slotId);
        booking.setCancelled(false);

        bookingMap.put(booking.getFlipFitBookingId(), booking);
        FlipFitGymOwnerServiceImpl.flipFitSlotMap.put(newSlot.getSlotId(),newSlot);
        return booking;
    }


    @Override
    public boolean cancelFlipFitBooking(int bookingId) {
        // Step 1: Retrieve the booking
        FlipFitBooking booking = bookingMap.get(bookingId);

        if (booking == null) {
            System.out.println("❌ Cancellation failed: Booking ID " + bookingId + " does not exist.");
            return false;
        }

        if (booking.isCancelled()) {
            System.out.println("⚠️ Booking ID " + bookingId + " is already cancelled.");
            return false;
        }

        // Step 2: Mark booking as cancelled
        booking.setCancelled(true);

        // Step 3: Retrieve the slot and increment seatsAvailable
        FlipFitSlot slot = FlipFitGymOwnerServiceImpl.flipFitSlotMap.get(booking.getSlotId());

        if (slot != null) {
            slot.setSeatsAvailable(slot.getSeatsAvailable() + 1);
            System.out.println("✅ Booking ID " + bookingId + " cancelled successfully. Seat released for Slot ID " + slot.getSlotId() + ".");
            FlipFitGymOwnerServiceImpl.flipFitSlotMap.put(slot.getSlotId(),slot);
        } else {
            System.out.println("⚠️ Slot ID " + booking.getSlotId() + " not found. Seat count not updated.");
        }
        return true;
    }


    @Override
    public List<FlipFitSlot> getSlotsDetails(int gymId) {
        List<FlipFitSlot> filteredSlots = new ArrayList<>();

        for (FlipFitSlot slot : FlipFitGymOwnerServiceImpl.flipFitSlotMap.values()) {
            if (slot.getGymId() == gymId) {
                filteredSlots.add(slot);
            }
        }

        if (filteredSlots.isEmpty()) {
            System.out.println(ColorConstants.RED + "❗ No slots found for Gym ID: " + gymId + ColorConstants.RESET);
        }

        return filteredSlots;
    }
}
