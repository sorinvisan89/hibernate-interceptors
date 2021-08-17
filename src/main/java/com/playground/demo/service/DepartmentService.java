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

        return mapDepartments(departments);
    }

    public List<DepartmentDTO> findDepartments() {

        final List<Department> departments = departmentRepository.findAll();

        return mapDepartments(departments);
    }

    public DepartmentDTO addDepartment(DepartmentRequestDTO departmentRequestDTO) {

        final Department toSave = new Department();
        toSave.setDepartmentName(departmentRequestDTO.getName());

        final Department savedDepartment = departmentRepository.save(toSave);

        return CustomMapper.mapDepartment(savedDepartment);
    }

    public DepartmentDTO updateDepartment(final Integer departmentId, DepartmentRequestDTO updateDepartment) {

        final Department existingDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department does not exist!"));

        final String oldName = existingDepartment.getDepartmentName();
        final String newName = updateDepartment.getName();

        existingDepartment.setDepartmentName(newName);

        if ("noUpdate".equals(newName)) {
            return CustomMapper.mapDepartment(new Department(existingDepartment.getDepartmentId(), oldName, existingDepartment.getEmployees()));

        } else {
            final Department saved = departmentRepository.save(existingDepartment);
            return CustomMapper.mapDepartment(saved);
        }
    }

    public DepartmentDTO deleteDepartment(final Integer departmentId) {

        final Department toDelete = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department does not exist!"));

        departmentRepository.delete(toDelete);

        return CustomMapper.mapDepartment(toDelete);
    }

    public DepartmentDTO getDepartmentById(final Integer departmentId) {

        final Department existingDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department does not exist!"));

        return CustomMapper.mapDepartment(existingDepartment);
    }


    private List<DepartmentDTO> mapDepartments(final List<Department> departments) {
        return departments.stream()
                .map(CustomMapper::mapDepartment)
                .collect(Collectors.toList());

    }

}
