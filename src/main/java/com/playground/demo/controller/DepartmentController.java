package com.playground.demo.controller;

import com.playground.demo.model.DepartmentDTO;
import com.playground.demo.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(final DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/v1/departments")
    @Tag(name = "Bad", description = "Not optimal")
    @Operation(description = "Will trigger n + 1 queries")
    ResponseEntity<List<DepartmentDTO>> getV1Departments() {
        final List<DepartmentDTO> result = departmentService.findDepartments();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/v2/departments")
    @Tag(name = "Good", description = "Optimal")
    @Operation(description = "Will trigger only 1 query")
    ResponseEntity<List<DepartmentDTO>> getV2Departments() {
        final List<DepartmentDTO> result = departmentService.fetchDepartments();
        return ResponseEntity.ok(result);
    }
}
