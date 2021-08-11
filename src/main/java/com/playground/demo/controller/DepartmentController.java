package com.playground.demo.controller;

import com.playground.demo.model.DepartmentDTO;
import com.playground.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(final DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<DepartmentDTO> getDepartments(@PathVariable(name = "id") final Integer departmentId) {
        final DepartmentDTO result = departmentService.getDepartment(departmentId);
        return ResponseEntity.ok(result);
    }
}
