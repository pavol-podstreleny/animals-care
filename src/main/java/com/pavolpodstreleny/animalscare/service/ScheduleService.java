package com.pavolpodstreleny.animalscare.service;

import java.util.List;

import javax.transaction.Transactional;

import com.pavolpodstreleny.animalscare.entity.Schedule;
import com.pavolpodstreleny.animalscare.repository.ScheduleRepository;
import com.pavolpodstreleny.animalscare.service.interfaces.IScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ScheduleService implements IScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public Schedule create(Schedule schedule, List<Long> employeeIDs, List<Long> petIDs) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Schedule> getSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> findScheduleByPetId(long petID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Schedule> findSchedulePyEmployeeId(long employeeID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Schedule> findScheduleByCustomer(long customerID) {
        // TODO Auto-generated method stub
        return null;
    }

}
