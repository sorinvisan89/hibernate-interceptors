package com.playground.demo.service;

import com.playground.demo.entity.Department;
import com.playground.demo.entity.Employee;
import com.playground.demo.mapper.CustomMapper;
import com.playground.demo.model.EmployeeDTO;
import com.playground.demo.model.EmployeeRequestDTO;
import com.playground.demo.repository.DepartmentRepository;
import com.playground.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final CustomMapper customMapper;

    @Autowired
    public EmployeeService(
            final EmployeeRepository employeeRepository,
            final DepartmentRepository departmentRepository,
            final CustomMapper customMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.customMapper = customMapper;
    }

    public List<EmployeeDTO> fetchEmployees() {

        final List<Employee> employees = employeeRepository.fetchEmployees();

        return customMapper.mapEmployees(employees);
    }

    public List<EmployeeDTO> findAllEmployees() {

        final List<Employee> employees = employeeRepository.findAll();

        return customMapper.mapEmployees(employees);
    }

    public EmployeeDTO addEmployee(final EmployeeRequestDTO employeeRequest) {
        final Department department = departmentRepository.findById(employeeRequest.getDepartmentId())
                .orElseThrow(() -> new EntityNotFoundException("No department found!"));


        final Employee employee = new Employee();
        employee.setName(employeeRequest.getName());
        employee.setSalary(employeeRequest.getSalary());

        employee.setParentDepartment(department);
        department.getEmployees().add(employee);

        final Employee saved = employeeRepository.save(employee);
        return customMapper.mapEmployee(saved);
    }

}
