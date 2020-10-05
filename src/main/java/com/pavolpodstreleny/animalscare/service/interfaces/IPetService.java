package com.pavolpodstreleny.animalscare.service.interfaces;

import java.util.List;

import com.pavolpodstreleny.animalscare.entity.Pet;

public interface IPetService {
    Pet save(Pet pet, long ownerID);

    Pet getPetById(long id);

    List<Pet> getPets();

    List<Pet> getPetsByOwner(long id);

    List<Pet> findAllByIDs(List<Long> ids);
}
