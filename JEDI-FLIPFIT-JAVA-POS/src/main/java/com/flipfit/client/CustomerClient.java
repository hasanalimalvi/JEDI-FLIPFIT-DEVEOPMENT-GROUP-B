package com.flipfit.client;

import com.flipfit.business.CustomerBusiness;

public class CustomerClient {
    public static void main(String[] args) {
        CustomerBusiness business = new CustomerBusiness();
        business.createCustomer();
        System.out.println("Update Customer " + business.updateCustomer(1));
        System.out.println("Delete Customer " + business.deleteCustomer(1));
    }
}
