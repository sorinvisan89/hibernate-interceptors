package com.playground.demo.service;

import com.playground.demo.entity.Department;
import com.playground.demo.model.DepartmentDTO;
import com.playground.demo.model.EmployeeDTO;
import com.playground.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(final DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentDTO getDepartment(final Integer departmentId){

        final Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("No department found!"));

        return mapDepartment(department);
    }


    private DepartmentDTO mapDepartment(final Department department){

        final DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setId(department.getDepartmentId());
        department.setDepartmentName(department.getDepartmentName());

        final List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        department.getEmployeeList().forEach(employee -> {
            final EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getEmployeeId());
            employeeDTO.setName(employee.getName());

            employeeDTOS.add(employeeDTO);
        });

        departmentDTO.setEmployees(
                Optional.of(employeeDTOS)
                        .filter(List::isEmpty)
                        .orElse(null)
        );

        return departmentDTO;
    }

}
