package com.flipfit.rest;



import com.flipfit.bean.*;
import com.flipfit.business.FlipFitGymOwnerService;
import com.flipfit.business.FlipFitGymOwnerServiceImpl;
import com.flipfit.exception.EntityNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("/owner")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlipFitGymOwnerRestController {

    private final FlipFitGymOwnerService gymOwnerService;

    public FlipFitGymOwnerRestController() {
        this.gymOwnerService = new FlipFitGymOwnerServiceImpl();
    }

    // ➕ Add Gym
    @POST
    @Path("/gym")
    public Response addGym(FlipFitGym gym) {
        gym.setApproved(false);
        return Response.ok(gymOwnerService.addGym(gym)).build();
    }

    // 🏋️ View Gyms by Owner ID
    @GET
    @Path("/gyms/{ownerId}")
    public Response viewGyms(@PathParam("ownerId") int ownerId) {
        return Response.ok(gymOwnerService.viewGyms(ownerId)).build();
    }

    // ➕ Add Slot
    @POST
    @Path("/slot")
    public Response addSlot(FlipFitSlot slot) {
        gymOwnerService.addSlot(slot);
        return Response.ok("✅ Slot added successfully").build();
    }

    // 🗑️ Delete Slot
    @DELETE
    @Path("/slot/{slotId}")
    public Response deleteSlot(@PathParam("slotId") int slotId) throws EntityNotFoundException {
        boolean deleted = gymOwnerService.deleteSlot(slotId);
        if (deleted) {
            return Response.ok("✅ Slot deleted successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("❌ Slot not found").build();
        }
    }

    // 📖 View Bookings by Gym ID
    @GET
    @Path("/bookings/{gymId}")
    public Response viewBookings(@PathParam("gymId") int gymId) throws EntityNotFoundException {
        return Response.ok(gymOwnerService.viewBookings(gymId)).build();
    }

    // 👁️ View Profile
    @GET
    @Path("/profile/{ownerId}")
    public Response viewProfile(@PathParam("ownerId") int ownerId) {
        FlipFitGymOwner owner = gymOwnerService.viewDetails(ownerId);
        if (owner != null) {
            return Response.ok(owner).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("❌ Gym owner not found").build();
        }
    }

    // ✏️ Edit Profile
    @PUT
    @Path("/profile")
    public Response editProfile(FlipFitGymOwner updatedOwner) {
        gymOwnerService.editDetails(updatedOwner);
        return Response.ok("✅ Gym owner profile updated").build();
    }

    // 💳 View Payments by Gym ID
    @GET
    @Path("/payments/{gymId}")
    public Response viewPayments(@PathParam("gymId") int gymId) throws EntityNotFoundException {
        return Response.ok(gymOwnerService.viewTransactions(gymId)).build();
    }

    // 🗑️ Delete Gym
    @DELETE
    @Path("/gym/{gymId}")
    public Response deleteGym(@PathParam("gymId") int gymId) throws EntityNotFoundException {
        boolean deleted = gymOwnerService.deleteGym(gymId);
        if (deleted) {
            return Response.ok("✅ Gym deleted successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("❌ Gym not found").build();
        }
    }

    // 📖 View Slots by Gym ID and Date
    @GET
    @Path("/slots/{gymId}/{date}")
    public Response viewSlots(@PathParam("gymId") int gymId, @PathParam("date") String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return Response.ok(gymOwnerService.viewSlots(gymId, date)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("❌ Invalid date format").build();
        }
    }

    // 🔓 Logout
    @GET
    @Path("/logout")
    public Response logout() {
        FlipFitGymOwnerServiceImpl.loggedInGymOwner = null;
        return Response.ok("🔓 Logged out successfully").build();
    }
}
