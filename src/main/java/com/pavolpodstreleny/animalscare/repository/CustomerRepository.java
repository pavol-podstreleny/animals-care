package com.pavolpodstreleny.animalscare.repository;

import java.util.Optional;

import com.pavolpodstreleny.animalscare.entity.Customer;
import com.pavolpodstreleny.animalscare.entity.Pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPets(Pet pet);
}