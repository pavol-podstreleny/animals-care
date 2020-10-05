package com.pavolpodstreleny.animalscare.service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.pavolpodstreleny.animalscare.entity.Employee;
import com.pavolpodstreleny.animalscare.entity.EmployeeSkill;
import com.pavolpodstreleny.animalscare.exception.EmployeeDoesNotExistException;
import com.pavolpodstreleny.animalscare.repository.EmployeeRepository;
import com.pavolpodstreleny.animalscare.service.interfaces.IEmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EntityManager entityManager;

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getById(long employeeID) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeID);
        return optionalEmployee.orElseThrow(() -> new EmployeeDoesNotExistException(employeeID));
    }

    @Override
    public void changeAvailableDays(long employeeID, Set<DayOfWeek> availableDays) {
        Employee employee = entityManager.find(Employee.class, employeeID);
        if (employee == null)
            throw new EmployeeDoesNotExistException(employeeID);
        employee.setDaysAvailable(availableDays);
    }

    @Override
    public List<Employee> findAvailableEmployees(DayOfWeek requiredDate, Set<EmployeeSkill> requiredSkill) {
        List<Employee> employees = employeeRepository.findEmployeeByDaysAvailable(requiredDate);
        return employees.stream().filter((Employee e) -> e.getSkills().containsAll(requiredSkill))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> findAllByIDs(List<Long> ids) {
        return employeeRepository.findAllById(ids);
    }

}