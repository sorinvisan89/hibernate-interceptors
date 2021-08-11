package com.playground.demo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeDTO {

    private Integer id;
    private String name;
    private BigDecimal salary;
}
