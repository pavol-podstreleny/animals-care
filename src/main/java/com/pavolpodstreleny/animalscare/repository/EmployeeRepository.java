package com.pavolpodstreleny.animalscare.repository;

import java.time.DayOfWeek;
import java.util.List;

import com.pavolpodstreleny.animalscare.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findEmployeeByDaysAvailable(DayOfWeek dayOfWeek);
}