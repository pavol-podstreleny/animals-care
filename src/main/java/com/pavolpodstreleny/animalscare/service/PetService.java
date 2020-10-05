package com.pavolpodstreleny.animalscare.service;

import java.util.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.pavolpodstreleny.animalscare.entity.Pet;
import com.pavolpodstreleny.animalscare.exception.PetDoesNotExistException;
import com.pavolpodstreleny.animalscare.repository.PetRepository;
import com.pavolpodstreleny.animalscare.service.interfaces.IPetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PetService implements IPetService {

    @Autowired
    EntityManager em;

    @Autowired
    private PetRepository petRepository;

    @Override
    public Pet getPetById(long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        return optionalPet.orElseThrow(() -> new PetDoesNotExistException("Pet with id " + id + " does not exists"));
    }

    @Override
    public List<Pet> findAllByIDs(List<Long> ids) {
        return petRepository.findAllById(ids);
    }

    @Override
    public List<Pet> getPets() {
        return petRepository.findAll();
    }

    @Override
    public List<Pet> getPetsByOwner(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Pet save(Pet pet, long ownerID) {
        // TODO Auto-generated method stub
        return null;
    }
}