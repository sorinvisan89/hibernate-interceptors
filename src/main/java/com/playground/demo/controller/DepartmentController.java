package com.playground.demo.controller;

import com.playground.demo.model.DepartmentDTO;
import com.playground.demo.model.DepartmentRequestDTO;
import com.playground.demo.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/v2/departments")
    @Tag(name = "Good", description = "Optimal")
    @Operation(description = "Add a department")
    ResponseEntity<DepartmentDTO> addDepartment(@RequestBody @Valid DepartmentRequestDTO departmentRequestDTO) {
        final DepartmentDTO result = departmentService.addDepartment(departmentRequestDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/v2/departments/{id}")
    @Tag(name = "Good", description = "Optimal")
    @Operation(description = "Delete a department by Id")
    ResponseEntity<DepartmentDTO> deleteDepartment(@PathVariable("id") Integer departmentId) {
        final DepartmentDTO result = departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/v2/departments/{id}")
    @Tag(name = "Good", description = "Optimal")
    @Operation(description = "Updated a department by Id")
    ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable("id") Integer departmentId, @RequestBody @Valid DepartmentRequestDTO departmentRequestDTO) {
        final DepartmentDTO result = departmentService.updateDepartment(departmentId, departmentRequestDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/v2/departments/{id}")
    @Tag(name = "Good", description = "Optimal")
    @Operation(description = "Get a department by Id")
    ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable("id") Integer departmentId) {
        final DepartmentDTO result = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(result);
    }
}
