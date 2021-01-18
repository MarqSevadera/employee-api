package com.savvytest.employeeapi.controllers;

import com.savvytest.employeeapi.models.Employee;
import com.savvytest.employeeapi.models.ResponseDto;
import com.savvytest.employeeapi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<ResponseDto> getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        return ResponseEntity.ok(ResponseDto.builder().setData(employeeList).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getEmployeeById(@PathVariable long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(ResponseDto.builder().setData(employee).build());
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(ResponseDto.builder().setData(savedEmployee).setMessage("Employee successfully created.").build());
    }

}

