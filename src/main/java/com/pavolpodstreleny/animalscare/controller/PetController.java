package com.pavolpodstreleny.animalscare.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.pavolpodstreleny.animalscare.dto.PetDTO;
import com.pavolpodstreleny.animalscare.entity.Pet;
import com.pavolpodstreleny.animalscare.exception.PetDoesNotExistException;
import com.pavolpodstreleny.animalscare.service.interfaces.IPetService;
import com.pavolpodstreleny.animalscare.utils.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private IPetService petService;

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        return transformPetEntityToDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> pets = petService.getPets();
        checkPetsEmptiness(pets);
        return pets.stream().map(this::transformPetEntityToDTO).collect(Collectors.toList());
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = Transformer.transformToDTO(petDTO, Pet.class);
        return transformPetEntityToDTO(petService.save(pet, petDTO.getOwnerId()));
    }

    private void checkPetsEmptiness(List<Pet> pets) {
        if (pets.isEmpty()) {
            throw new PetDoesNotExistException("No pets exists");
        }
    }

    private PetDTO transformPetEntityToDTO(Pet pet) {
        PetDTO petDTO = Transformer.transformToDTO(pet, PetDTO.class);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

}