package com.savvytest.employeeapi.services;

import com.savvytest.employeeapi.exceptions.EmployeeNotFoundException;
import com.savvytest.employeeapi.models.Employee;
import com.savvytest.employeeapi.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(long id) {
        Optional<Employee> opt = employeeRepository.findById(id);
        if (opt.isEmpty())
            throw new EmployeeNotFoundException("Employee not found");
        return opt.get();
    }


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
