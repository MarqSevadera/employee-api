package com.savvytest.employeeapi.repositories;

import com.savvytest.employeeapi.models.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest

public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void testFindById() {
        Employee savedEmployee = testEntityManager.persistFlushFind(new Employee(0, "Marque Sevadera", 120000, 22, ""));
        assertThat(savedEmployee.getId()).isNotNull().isNotNegative();

        Optional<Employee> opt = employeeRepository.findById(1L);
        assertThat(opt.isPresent());

    }

    @Test
    public void testFindAll() {
        testEntityManager.persistFlushFind(new Employee(0, "Marque Sevadera", 120000, 22, ""));
        testEntityManager.persistFlushFind(new Employee(0, "John Doe", 120000, 22, ""));

        List<Employee> employeeList = employeeRepository.findAll();
        assertThat(employeeList.size()).isEqualTo(2);
    }
}
