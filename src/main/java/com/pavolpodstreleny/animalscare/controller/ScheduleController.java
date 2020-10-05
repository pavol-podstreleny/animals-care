package com.pavolpodstreleny.animalscare.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.pavolpodstreleny.animalscare.dto.ScheduleDTO;
import com.pavolpodstreleny.animalscare.entity.Employee;
import com.pavolpodstreleny.animalscare.entity.Pet;
import com.pavolpodstreleny.animalscare.entity.Schedule;
import com.pavolpodstreleny.animalscare.exception.ScheduleDoesNotExistException;
import com.pavolpodstreleny.animalscare.service.interfaces.IScheduleService;
import com.pavolpodstreleny.animalscare.utils.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    IScheduleService scheduleService;

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getSchedules();
        checkScheduleEmptiness(schedules);
        return schedules.stream().map(this::transformScheduleEntityToDTO).collect(Collectors.toList());
    }

    private void checkScheduleEmptiness(List<Schedule> schedules) {
        if (schedules.isEmpty()) {
            throw new ScheduleDoesNotExistException("No schedule exist");
        }
    }

    private ScheduleDTO transformScheduleEntityToDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = Transformer.transformToDTO(schedule, ScheduleDTO.class);

        if (schedule.getPets() != null) {
            List<Long> petIds = schedule.getPets().stream().map((Pet::getId)).collect(Collectors.toList());
            if (!petIds.isEmpty()) {
                scheduleDTO.setPetIds(petIds);
            }
        }

        if (schedule.getEmployees() != null) {
            List<Long> employeeIds = schedule.getEmployees().stream().map((Employee::getId))
                    .collect(Collectors.toList());
            if (!employeeIds.isEmpty()) {
                scheduleDTO.setEmployeeIds(employeeIds);
            }
        }

        return scheduleDTO;
    }

}