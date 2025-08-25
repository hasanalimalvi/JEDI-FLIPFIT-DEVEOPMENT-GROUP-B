package com.flipfit.rest;



import com.flipfit.bean.FlipFitDirectCustomer;
import com.flipfit.bean.FlipFitGymOwner;
import com.flipfit.business.FlipFitAdminService;
import com.flipfit.business.FlipFitDirectCustomerService;
import com.flipfit.business.FlipFitGymOwnerService;
import com.flipfit.business.FlipFitAdminServiceImpl;
import com.flipfit.business.FlipFitDirectCustomerServiceImpl;
import com.flipfit.business.FlipFitGymOwnerServiceImpl;
import com.flipfit.exception.EntityNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlipFitUserRestController {

    private final FlipFitDirectCustomerService directCustomerService;
    private final FlipFitGymOwnerService gymOwnerService;
    private final FlipFitAdminService adminService;

    public FlipFitUserRestController() {
        this.directCustomerService = new FlipFitDirectCustomerServiceImpl();
        this.gymOwnerService = new FlipFitGymOwnerServiceImpl();
        this.adminService = new FlipFitAdminServiceImpl();
    }

    // 🔐 Unified Login Endpoint
    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        try {
            Object user = switch (request.getRole().toLowerCase()) {
                case "customer" -> directCustomerService.login(request.getUsername(), request.getPassword());
                case "owner" -> gymOwnerService.login(request.getUsername(), request.getPassword());
                case "admin" -> adminService.login(request.getUsername(), request.getPassword());
                default -> null;
            };

            if (user != null) {
                return Response.ok(user).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("❌ Login failed. Invalid credentials.").build();
            }


        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("⚠️ Unexpected error: " + e.getMessage()).build();
        }
    }

    // 📝 Register Gym Customer
    @POST
    @Path("/register/customer")
    public Response registerCustomer(FlipFitDirectCustomer customer) {
        try {
            customer.setRoleId(1);
            directCustomerService.registerCustomer(customer);
            return Response.status(Response.Status.CREATED).entity(customer).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // 🏢 Register Gym Owner
    @POST
    @Path("/register/owner")
    public Response registerOwner(FlipFitGymOwner owner) {
        try {
            owner.setRoleId(2);
            owner.setIsApproved(false);
            gymOwnerService.registerGymOwner(owner);
            return Response.status(Response.Status.CREATED).entity(owner).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }



    // 📥 Login Request DTO
    public static class LoginRequest {
        private String username;
        private String password;
        private String role;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}
