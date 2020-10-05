package com.pavolpodstreleny.animalscare.service.interfaces;

import java.util.List;

import com.pavolpodstreleny.animalscare.entity.Schedule;

public interface IScheduleService {
    Schedule create(Schedule schedule, List<Long> employeeIDs, List<Long> petIDs);

    List<Schedule> getSchedules();

    List<Schedule> findScheduleByPetId(long petID);

    List<Schedule> findSchedulePyEmployeeId(long employeeID);

    List<Schedule> findScheduleByCustomer(long customerID);
}
