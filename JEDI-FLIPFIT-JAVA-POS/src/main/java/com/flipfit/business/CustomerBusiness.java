package com.flipfit.business;

public class CustomerBusiness {

    public void createCustomer(){
        System.out.println("Creating Customer");
    }

    public boolean updateCustomer(int id){
        System.out.println("Updated customer with id " + id);
        return true;
    }

    public boolean deleteCustomer(int id){
        System.out.println("Deleting customer with id " + id);
        return true;
    }

    public void listCustomers(){
        System.out.println("List the customers");
    }
}
