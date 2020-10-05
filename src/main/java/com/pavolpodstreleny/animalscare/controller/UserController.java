package com.pavolpodstreleny.animalscare.controller;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.pavolpodstreleny.animalscare.dto.CustomerDTO;
import com.pavolpodstreleny.animalscare.dto.EmployeeDTO;
import com.pavolpodstreleny.animalscare.dto.EmployeeRequestDTO;
import com.pavolpodstreleny.animalscare.entity.Customer;
import com.pavolpodstreleny.animalscare.entity.Employee;
import com.pavolpodstreleny.animalscare.entity.EmployeeSkill;
import com.pavolpodstreleny.animalscare.entity.Pet;
import com.pavolpodstreleny.animalscare.exception.CustomerDoesNotExistException;
import com.pavolpodstreleny.animalscare.exception.EmployeeDoesNotExistException;
import com.pavolpodstreleny.animalscare.service.interfaces.ICustomerService;
import com.pavolpodstreleny.animalscare.service.interfaces.IEmployeeService;
import com.pavolpodstreleny.animalscare.utils.Transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    ICustomerService customerService;

    @Autowired
    IEmployeeService employeeService;

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerService.getCustomers();
        checkCustomerEmptiness(customers);
        return customers.stream().map(this::transformCustomerEntityToDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Customer customer = customerService.getCustomerByPetId(petId);
        return transformCustomerEntityToDTO(customer);
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer = Transformer.transformToEntity(customerDTO, Customer.class);
        return transformCustomerEntityToDTO(customerService.save(customer));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = Transformer.transformToEntity(employeeDTO, Employee.class);
        return transformEmployeeEntityToDTO(employeeService.save(employee));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getById(employeeId);
        return transformEmployeeEntityToDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.changeAvailableDays(employeeId, daysAvailable);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        DayOfWeek requiredDate = employeeDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> requiredSkill = employeeDTO.getSkills();
        List<Employee> employees = employeeService.findAvailableEmployees(requiredDate, requiredSkill);
        checkEmployeeEmptiness(employees);
        return employees.stream().map(this::transformEmployeeEntityToDTO).collect(Collectors.toList());
    }

    private EmployeeDTO transformEmployeeEntityToDTO(Employee employee) {
        return Transformer.transformToDTO(employee, EmployeeDTO.class);
    }

    private CustomerDTO transformCustomerEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = Transformer.transformToDTO(customer, CustomerDTO.class);
        if (customer.getPets() != null) {
            List<Long> petIds = customer.getPets().stream().map((Pet::getId)).collect(Collectors.toList());
            if (!petIds.isEmpty()) {
                customerDTO.setPetIds(petIds);
            }
        }
        return customerDTO;
    }

    private void checkEmployeeEmptiness(List<Employee> employees) {
        if (employees.isEmpty()) {
            throw new EmployeeDoesNotExistException("No employee exist");
        }
    }

    private void checkCustomerEmptiness(List<Customer> customers) {
        if (customers.isEmpty()) {
            throw new CustomerDoesNotExistException("No customer exist");
        }
    }

}