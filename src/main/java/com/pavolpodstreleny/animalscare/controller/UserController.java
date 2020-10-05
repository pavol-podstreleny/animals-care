package com.pavolpodstreleny.animalscare.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.pavolpodstreleny.animalscare.dto.CustomerDTO;
import com.pavolpodstreleny.animalscare.entity.Customer;
import com.pavolpodstreleny.animalscare.entity.Pet;
import com.pavolpodstreleny.animalscare.exception.CustomerDoesNotExistException;
import com.pavolpodstreleny.animalscare.service.interfaces.ICustomerService;
import com.pavolpodstreleny.animalscare.utils.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    ICustomerService customerService;

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.getCustomers();
        checkCustomerEmptiness(customers);
        return customers.stream().map(this::transformCustomerEntityToDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Customer customer = customerService.getCustomerByPetId(petId);
        return transformCustomerEntityToDTO(customer);
    }

    private CustomerDTO transformCustomerEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = Transformer.transformToDTO(customer, CustomerDTO.class);
        if (customer.getPets() != null) {
            List<Long> petIds = customer.getPets().stream().map((Pet::getId)).collect(Collectors.toList());
            if (!petIds.isEmpty()) {
                customerDTO.setPetIds(petIds);
            }
        }
        return customerDTO;
    }

    private void checkCustomerEmptiness(List<Customer> customers) {
        if (customers.isEmpty()) {
            throw new CustomerDoesNotExistException("No customer exist");
        }
    }

}