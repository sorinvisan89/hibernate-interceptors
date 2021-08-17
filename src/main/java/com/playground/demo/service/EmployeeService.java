package com.playground.demo.service;

import com.playground.demo.entity.Employee;
import com.playground.demo.mapper.CustomMapper;
import com.playground.demo.model.EmployeeDTO;
import com.playground.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> fetchEmployees(){

        final List<Employee> employees = employeeRepository.fetchEmployees();

        return mapEmployees(employees);
    }

    public List<EmployeeDTO> findAllEmployees(){

        final List<Employee> employees = employeeRepository.findAll();

        return mapEmployees(employees);
    }


    private List<EmployeeDTO> mapEmployees(final List<Employee> employees){
        return employees.stream()
                .map(CustomMapper::mapEmployee)
                .collect(Collectors.toList());
    }
}
