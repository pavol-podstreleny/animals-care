package com.pavolpodstreleny.animalscare.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.pavolpodstreleny.animalscare.entity.Customer;
import com.pavolpodstreleny.animalscare.exception.CustomerDoesNotExistException;
import com.pavolpodstreleny.animalscare.repository.CustomerRepository;
import com.pavolpodstreleny.animalscare.repository.PetRepository;
import com.pavolpodstreleny.animalscare.service.interfaces.ICustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    @Override
    public Customer getByID(long customerID) {
        Optional<Customer> customer = customerRepository.findById(customerID);
        return customer.orElseThrow(
                () -> new CustomerDoesNotExistException("Customer with id " + customerID + " does not exists"));
    }

    @Override
    public Customer save(Customer customerDTO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerByPetId(long petID) {
        // TODO Auto-generated method stub
        return null;
    }

}