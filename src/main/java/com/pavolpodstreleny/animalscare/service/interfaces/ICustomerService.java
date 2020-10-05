package com.pavolpodstreleny.animalscare.service.interfaces;

import java.util.List;

import com.pavolpodstreleny.animalscare.entity.Customer;

public interface ICustomerService {
    Customer save(Customer customerDTO);

    Customer getByID(long customerID);

    List<Customer> getCustomers();

    Customer getCustomerByPetId(long petID);
}