package com.savvytest.employeeapi.services;

import com.savvytest.employeeapi.exceptions.EmployeeNotFoundException;
import com.savvytest.employeeapi.models.Employee;
import com.savvytest.employeeapi.repositories.EmployeeRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @Before
    public void init() {
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    public void getEmployeeById_shouldReturnEmployeeInfo() {
        Mockito.when(employeeRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Employee(1L, "Marque Sevadera", 120000, 22, "")));

        Employee employee = employeeService.getEmployeeById(1L);
        assertThat(employee.getName(), Matchers.equalToIgnoringCase("marque sevadera"));
        assertThat(employee.getSalary(), Matchers.equalTo(120000D));
        assertThat(employee.getAge(), Matchers.equalTo(22));
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void getNonExistentEmployeeId_shouldThrowEmployeeNotFoundException() {
        Mockito.when(employeeRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        employeeService.getEmployeeById(1L);

    }

    @Test
    public void getAllEmployees_shouldReturnListOfEmployees() {
        Mockito.when(employeeRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<Employee> employeeList = employeeService.getAllEmployees();
        assertThat(employeeList.size(), Matchers.equalTo(0));
    }

    @Test
    public void createEmployee_shouldReturnCreatedEmployee() {
        Employee emp = new Employee(1L, "Marque Sevadera", 120000, 22, "");
        Mockito.when(employeeRepository.save(emp)).thenReturn(emp);

        Employee savedEmployee = employeeService.createEmployee(emp);
        assertThat(savedEmployee.getId(), Matchers.equalTo(emp.getId()));
        assertThat(savedEmployee.getName(), Matchers.equalToIgnoringCase(emp.getName()));
    }
}
