package com.savvytest.employeeapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.savvytest.employeeapi.exceptions.EmployeeNotFoundException;
import com.savvytest.employeeapi.models.Employee;
import com.savvytest.employeeapi.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@RunWith(SpringRunner.class)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    private Employee marque;
    private Employee john;

    @Before
    public void init() {
        marque = new Employee(1L, "Marque Sevadera", 120000, 22, "");
        john = new Employee(2L, "John Doe", 1000, 45, "");
    }

    @Test
    public void getEmployee_shouldReturnEmployee() throws Exception {
        Mockito.when(employeeService.getEmployeeById(anyLong()))
                .thenReturn(marque);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/1").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data").isNotEmpty())
                .andExpect(jsonPath("$.['data'].['id']").value(1))
                .andExpect(jsonPath("$.['data'].['name']").value("Marque Sevadera"))
                .andExpect(jsonPath("$.['data'].['salary']").value(120000D))
                .andExpect(jsonPath("$.['data'].['age']").value(22))
                .andExpect(jsonPath("$.['data'].['image_url']").isEmpty());
    }

    @Test
    public void getAllEmployees_shouldReturnListOfEmployees() throws Exception {
        Mockito.when(employeeService.getAllEmployees())
                .thenReturn(Arrays.asList(marque, john));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data").isArray());
    }


    @Test
    public void createNewEmployee_shouldReturnCreatedEmployee() throws Exception {
        Mockito.when(employeeService.createEmployee(any(Employee.class)))
                .thenReturn(john);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/employees")
                .content(objectMapper.writeValueAsString(john))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data").isNotEmpty())
                .andExpect(jsonPath("$.['data'].['id']").value(john.getId()))
                .andExpect(jsonPath("$.['data'].['name']").value(john.getName()))
                .andExpect(jsonPath("$.['data'].['salary']").value(john.getSalary()))
                .andExpect(jsonPath("$.['data'].['age']").value(john.getAge()))
                .andExpect(jsonPath("$.['data'].['image_url']").value(john.getImageUrl()));
    }

    @Test
    public void getNonExistentEmployee_shouldThrowRecordNotFoundException() throws Exception {
        Mockito.when(employeeService.getEmployeeById(anyLong()))
                .thenThrow(new EmployeeNotFoundException("Employee not found!"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees/1"))
                .andExpect(status().isNotFound());
    }
}
