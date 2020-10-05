package com.pavolpodstreleny.animalscare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.pavolpodstreleny.animalscare.entity.Employee;
import com.pavolpodstreleny.animalscare.entity.Pet;
import com.pavolpodstreleny.animalscare.entity.Schedule;
import com.pavolpodstreleny.animalscare.repository.ScheduleRepository;
import com.pavolpodstreleny.animalscare.service.interfaces.IEmployeeService;
import com.pavolpodstreleny.animalscare.service.interfaces.IPetService;
import com.pavolpodstreleny.animalscare.service.interfaces.IScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ScheduleService implements IScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    IPetService petService;

    @Autowired
    IEmployeeService employeeService;

    @Override
    public Schedule create(Schedule schedule, List<Long> employeeIDs, List<Long> petIDs) {
        List<Employee> employees = employeeService.findAllByIDs(employeeIDs);
        List<Pet> pets = petService.findAllByIDs(petIDs);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> findScheduleByPetId(long petID) {
        Pet pet = petService.getPetById(petID);
        return scheduleRepository.findSchedulesByPets(pet);
    }

    @Override
    public List<Schedule> findSchedulePyEmployeeId(long employeeID) {
        Employee employee = employeeService.getById(employeeID);
        return scheduleRepository.findSchedulesByEmployees(employee);
    }

    @Override
    public List<Schedule> findScheduleByCustomer(long customerID) {
        List<Pet> pets = petService.getPetsByOwner(customerID);

        ArrayList<Schedule> finalSchedules = new ArrayList<>();

        List<List<Schedule>> schedules = pets.stream().map((Pet p) -> findScheduleByPetId(p.getId()))
                .collect(Collectors.toList());

        schedules.forEach((List<Schedule> s) -> {
            s.forEach((Schedule actualSchedule) -> {
                if (!finalSchedules.contains(actualSchedule)) {
                    finalSchedules.add(actualSchedule);
                }
            });
        });

        return finalSchedules;
    }

}
