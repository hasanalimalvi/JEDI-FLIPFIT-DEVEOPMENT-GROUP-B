package com.flipfit.rest;



import com.flipfit.business.FlipFitAdminService;
import com.flipfit.business.FlipFitAdminServiceImpl;
import com.flipfit.exception.EntityNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlipFitAdminRestController {

    private final FlipFitAdminService flipFitAdminService;

    public FlipFitAdminRestController() {
        this.flipFitAdminService = new FlipFitAdminServiceImpl();
    }

    // 📨 View Pending Gym Owner Requests
    @GET
    @Path("/gym-owner/pending")
    public Response getPendingGymOwnerRequests() {
        return Response.ok(flipFitAdminService.getPendingGymOwnerList()).build();
    }

    // 🏢 View All Gym Owners
    @GET
    @Path("/gym-owners")
    public Response getAllGymOwners() {
        return Response.ok(flipFitAdminService.getGymOwners()).build();
    }

    // 🧍 View All Gym Customers
    @GET
    @Path("/customers")
    public Response getAllCustomers() {
        return Response.ok(flipFitAdminService.getCustomerList()).build();
    }

    // 🏋️ View All Gyms
    @GET
    @Path("/gyms")
    public Response getAllGyms() {
        return Response.ok(flipFitAdminService.getGyms()).build();
    }

    // 💳 View Payments
    @GET
    @Path("/payments")
    public Response viewPayments() {
        return Response.ok(flipFitAdminService.viewPayments()).build();
    }

    // ✅ Approve Gym Owner
    @PUT
    @Path("/gym-owner/approve/{id}")
    public Response approveGymOwner(@PathParam("id") int id) {
        try {
            flipFitAdminService.validateGymOwner(id);
            return Response.ok("✅ Gym Owner approved").build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("❌ " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("⚠️ " + e.getMessage()).build();
        }
    }

    // ✅ Approve Gym
    @PUT
    @Path("/gym/approve/{id}")
    public Response approveGym(@PathParam("id") int id) {
        try {
            flipFitAdminService.validateGym(id);
            return Response.ok("✅ Gym approved").build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("❌ " + e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("⚠️ " + e.getMessage()).build();
        }
    }

    // ✅ View Approved Gym Owner Requests
    @GET
    @Path("/gym-owner/approved")
    public Response getApprovedGymOwnerRequests() {
        return Response.ok(flipFitAdminService.getApprovedGymOwnerList()).build();
    }

    // 🕒 View Pending Gym Requests
    @GET
    @Path("/gym/pending")
    public Response getPendingGymRequests() {
        return Response.ok(flipFitAdminService.getPendingGymList()).build();
    }

    // ✅ View Approved Gym Requests
    @GET
    @Path("/gym/approved")
    public Response getApprovedGymRequests() {
        return Response.ok(flipFitAdminService.getApprovedGymList()).build();
    }

    // 🔓 Logout
    @GET
    @Path("/logout")
    public Response logout() {
        FlipFitAdminServiceImpl.loggedInAdmin = null;
        return Response.ok("🔓 Admin logged out successfully").build();
    }
}
