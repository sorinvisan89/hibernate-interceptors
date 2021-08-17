package com.playground.demo.service;

import com.playground.demo.entity.Department;
import com.playground.demo.mapper.CustomMapper;
import com.playground.demo.model.DepartmentDTO;
import com.playground.demo.model.DepartmentRequestDTO;
import com.playground.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(final DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> fetchDepartments() {

        final List<Department> departments = departmentRepository.fetchDepartments();

        return  mapDepartments(departments);
    }

    public List<DepartmentDTO> findDepartments() {

        final List<Department> departments = departmentRepository.findAll();

        return mapDepartments(departments);
    }

    public DepartmentDTO addDepartment(DepartmentRequestDTO departmentRequestDTO) {
        final Department toSave = new Department();
        toSave.setDepartmentName(departmentRequestDTO.getName());

        final Department savedDepartment = departmentRepository.save(toSave);

        return  CustomMapper.mapDepartment(savedDepartment);
    }


    private List<DepartmentDTO> mapDepartments(final List<Department> departments) {
        return departments.stream()
                .map(CustomMapper::mapDepartment)
                .collect(Collectors.toList());

    }

}
