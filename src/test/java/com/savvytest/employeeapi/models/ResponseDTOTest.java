package com.savvytest.employeeapi.models;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ResponseDTOTest {

    @Test
    public void testShouldConstruct() {
        ResponseDto responseDto = new ResponseDto("Success", "Fetched successfully",
                new Employee(1L, "Marque Sevadera", 120000, 22, "https://www.example.com/image-url"));
        Assert.assertThat(responseDto.getStatus(), Matchers.equalToIgnoringCase("success"));
        Assert.assertThat(responseDto.getMessage(), Matchers.equalToIgnoringCase("fetched successfully"));
        Assert.assertThat(responseDto.getData(),  Matchers.notNullValue());

    }
}
