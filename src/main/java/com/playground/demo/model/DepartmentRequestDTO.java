package com.playground.demo.model;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentRequestDTO {

    @NotNull
    private String name;
}
