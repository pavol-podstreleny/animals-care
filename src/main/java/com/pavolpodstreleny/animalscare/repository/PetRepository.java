package com.pavolpodstreleny.animalscare.repository;

import com.pavolpodstreleny.animalscare.entity.Customer;
import com.pavolpodstreleny.animalscare.entity.Pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("SELECT p FROM Pet p WHERE p.owner = :owner")
    List<Pet> findAllByOwner(Customer owner);
}