package com.playground.demo.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class EmployeeRequestDTO {

    @NotNull
    private String name;

    @NotNull
    private BigDecimal salary;

    @NotNull
    private Integer departmentId;
}
