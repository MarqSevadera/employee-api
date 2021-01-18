package com.savvytest.employeeapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "set")
@NoArgsConstructor
public class ResponseDto {
    @Builder.Default
    private String status = "Success";
    @Builder.Default
    private String message = "Record/s has been fetched successfully!";
    private Object data;

}
