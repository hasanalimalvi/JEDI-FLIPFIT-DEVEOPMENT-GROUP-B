package com.flipfit.rest;

import com.flipfit.bean.*;
import com.flipfit.business.FlipFitDirectCustomerService;
import com.flipfit.business.FlipFitDirectCustomerServiceImpl;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.PaymentFailedException;
import com.flipfit.exception.SlotsNotAvailableException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlipFitDirectCustomerRestController {

    FlipFitDirectCustomerService service = new FlipFitDirectCustomerServiceImpl();

    @GET
    @Path("/gyms")
    public List<FlipFitGym> viewGyms() {
        return service.viewGyms();
    }

    @GET
    @Path("/slots/{gymId}/{date}")
    public List<FlipFitSlotAvailability> viewSlots(@PathParam("gymId") int gymId,
                                                   @PathParam("date") String date) throws EntityNotFoundException {
        return service.viewSlots(gymId, LocalDate.parse(date));
    }

    @GET
    @Path("/bookings/{userId}")
    public List<FlipFitBooking> viewBookings(@PathParam("userId") int userId) {
        return service.viewBookedSlots(userId);
    }

    @POST
    @Path("/book")
    public FlipFitBooking bookSlot(FlipFitBooking booking) throws SlotsNotAvailableException, EntityNotFoundException {
        return service.makeFlipFitBooking(booking.userId, booking.getSlotId(), booking.getDate());
    }

    @DELETE
    @Path("/cancel/{bookingId}")
    public boolean cancelBooking(@PathParam("bookingId") int bookingId) throws EntityNotFoundException {
        return service.cancelFlipFitBooking(bookingId);
    }

    @GET
    @Path("/profile/{userId}")
    public FlipFitDirectCustomer viewProfile(@PathParam("userId") int userId) {
        return service.viewDetails(userId);
    }

    @PUT
    @Path("/profile")
    public FlipFitDirectCustomer editProfile(FlipFitDirectCustomer customer) {
        return service.editDetails(customer);
    }

    @POST
    @Path("/payment")
    public FlipFitTransaction makePayment(FlipFitTransaction transaction) throws EntityNotFoundException, PaymentFailedException {
        return service.makePayment(transaction);
    }

    @GET
    @Path("/logout/{userId}")
    public String logout(@PathParam("userId") int userId) {
        FlipFitDirectCustomerServiceImpl.loggedInDirectCustomer = null;
        return "User " + userId + " logged out successfully.";
    }
}
