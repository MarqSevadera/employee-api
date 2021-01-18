package com.savvytest.employeeapi.models;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class EmployeePOJOTest {

    @Test
    public void testShouldConstruct() {
        Employee employee = new Employee(1L, "Marque Sevadera", 120000, 22, "https://www.example.com/image-url");
        Assert.assertEquals(1L, employee.getId());
        Assert.assertThat(employee.getName(), Matchers.equalToIgnoringCase("Marque Sevadera"));
        Assert.assertThat(employee.getSalary(), Matchers.equalTo(120000D));
        Assert.assertThat(employee.getAge(), Matchers.equalTo(employee.getAge()));
        Assert.assertThat(employee.getImageUrl(), Matchers.equalToIgnoringCase("https://www.example.com/image-url"));
    }

}