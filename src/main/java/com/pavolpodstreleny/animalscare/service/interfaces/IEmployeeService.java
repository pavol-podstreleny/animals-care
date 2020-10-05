package com.pavolpodstreleny.animalscare.service.interfaces;

import java.time.DayOfWeek;
import java.util.*;

import com.pavolpodstreleny.animalscare.entity.Employee;
import com.pavolpodstreleny.animalscare.entity.EmployeeSkill;

public interface IEmployeeService {
    Employee save(Employee employee);

    Employee getById(long employeeID);

    void changeAvailableDays(long employeeID, Set<DayOfWeek> availableDays);

    List<Employee> findAvailableEmployees(DayOfWeek requiredDate, Set<EmployeeSkill> requiredSkill);

    List<Employee> findAllByIDs(List<Long> ids);
}