package com.pavolpodstreleny.animalscare.repository;

import com.pavolpodstreleny.animalscare.entity.Pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}