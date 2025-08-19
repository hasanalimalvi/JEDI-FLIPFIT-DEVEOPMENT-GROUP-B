package com.flipfit.client;

import com.flipfit.business.GymOwnerService;

public class CustomerClient {
    public static void main(String[] args) {
        GymOwnerService business = new GymOwnerService();
        business.createCustomer();
        System.out.println("Update Customer " + business.updateCustomer(1));
        System.out.println("Delete Customer " + business.deleteCustomer(1));
    }
}
