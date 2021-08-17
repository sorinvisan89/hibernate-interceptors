package com.playground.demo.controller;

import com.playground.demo.model.EmployeeDTO;
import com.playground.demo.model.EmployeeRequestDTO;
import com.playground.demo.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/v1/employees")
    @Tag(name = "Bad", description = "Not optimal")
    @Operation(description = "Will trigger n + 1 queries")
    ResponseEntity<List<EmployeeDTO>> getV1Employees() {
        final List<EmployeeDTO> result = employeeService.findAllEmployees();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/v2/employees")
    @Tag(name = "Good", description = "Optimal")
    @Operation(description = "Will trigger 1 query")
    ResponseEntity<List<EmployeeDTO>> getV2Employees() {
        final List<EmployeeDTO> result = employeeService.fetchEmployees();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/v2/employees")
    @Tag(name = "Good", description = "Optimal")
    ResponseEntity<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeRequestDTO employeeRequestDTO) {
        final EmployeeDTO result = employeeService.addEmployee(employeeRequestDTO);
        return ResponseEntity.ok(result);
    }

}