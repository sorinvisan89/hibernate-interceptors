package com.playground.demo.mapper;

import com.playground.demo.entity.Department;
import com.playground.demo.entity.Employee;
import com.playground.demo.model.DepartmentDTO;
import com.playground.demo.model.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomMapper {

    public static DepartmentDTO mapDepartment(final Department department) {
        final DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName(department.getDepartmentName());
        departmentDTO.setId(department.getDepartmentId());

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        department.getEmployees().forEach(employee -> {
            final EmployeeDTO employeeDTO = mapEmployee(employee);
            employeeDTOS.add(employeeDTO);
        });

        departmentDTO.setEmployees(
                Optional.of(employeeDTOS)
                        .filter(list -> !list.isEmpty())
                        .orElse(null)
        );
        return departmentDTO;
    }

    public static EmployeeDTO mapEmployee(final Employee employee) {

        final EmployeeDTO result = new EmployeeDTO();

        result.setId(employee.getEmployeeId());
        result.setName(employee.getName());
        result.setSalary(employee.getSalary());

        result.setDepartmentId(employee.getParentDepartment().getDepartmentId());
        result.setDepartmentName(employee.getParentDepartment().getDepartmentName());

        return result;
    }
}
