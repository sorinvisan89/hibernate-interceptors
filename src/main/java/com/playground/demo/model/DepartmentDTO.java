package com.playground.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDTO {

    private Integer id;

    private String name;

    private List<EmployeeDTO> employees;
}
