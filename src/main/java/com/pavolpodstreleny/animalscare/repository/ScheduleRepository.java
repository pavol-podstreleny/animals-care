package com.pavolpodstreleny.animalscare.repository;

import java.util.List;

import com.pavolpodstreleny.animalscare.entity.Employee;
import com.pavolpodstreleny.animalscare.entity.Pet;
import com.pavolpodstreleny.animalscare.entity.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findSchedulesByPets(Pet pet);

    List<Schedule> findSchedulesByEmployees(Employee employee);
}