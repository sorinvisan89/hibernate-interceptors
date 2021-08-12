package com.playground.demo.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class EmployeeDTO {

    @NotNull(message = "{employeeId.mandatory}")
    private Integer id;

    private String name;

    private BigDecimal salary;

    private String departmentName;

    private Integer departmentId;
}
