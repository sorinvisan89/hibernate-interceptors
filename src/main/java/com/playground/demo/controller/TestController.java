package com.playground.demo.controller;

import com.playground.demo.model.EmployeeDTO;
import com.playground.demo.model.TestDTO;
import com.playground.demo.model.TestRequestDTO;
import com.playground.demo.service.EmployeeService;
import com.playground.demo.service.TestService;
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
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(final TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/v1/test")
    ResponseEntity<TestDTO> addTest(@Valid @RequestBody final TestRequestDTO testRequestDTO) {
        final TestDTO result = testService.addTest(testRequestDTO);
        return ResponseEntity.ok(result);
    }

}
