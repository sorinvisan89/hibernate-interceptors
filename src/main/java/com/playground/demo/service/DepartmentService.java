package com.playground.demo.service;

import com.playground.demo.entity.Department;
import com.playground.demo.mapper.CustomMapper;
import com.playground.demo.model.DepartmentDTO;
import com.playground.demo.model.DepartmentRequestDTO;
import com.playground.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final CustomMapper customMapper;

    @Autowired
    public DepartmentService(
            final DepartmentRepository departmentRepository,
            final CustomMapper customMapper) {
        this.departmentRepository = departmentRepository;
        this.customMapper = customMapper;
    }

    public List<DepartmentDTO> fetchDepartments() {

        final List<Department> departments = departmentRepository.fetchDepartments();

        return customMapper.mapDepartments(departments);
    }

    public List<DepartmentDTO> findDepartments() {

        final List<Department> departments = departmentRepository.findAll();

        return customMapper.mapDepartments(departments);
    }

    public DepartmentDTO addDepartment(DepartmentRequestDTO departmentRequestDTO) {

        final Department toSave = new Department();
        toSave.setDepartmentName(departmentRequestDTO.getName());

        final Department savedDepartment = departmentRepository.save(toSave);

        return customMapper.mapDepartment(savedDepartment);
    }

    public DepartmentDTO updateDepartment(final Integer departmentId, DepartmentRequestDTO updateDepartment) {

        final Department existingDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department does not exist!"));

        final String oldName = existingDepartment.getDepartmentName();
        final String newName = updateDepartment.getName();

        existingDepartment.setDepartmentName(newName);

        if ("noUpdate".equals(newName)) {
            final Department original = new Department(existingDepartment.getDepartmentId(), oldName, existingDepartment.getEmployees());

            return customMapper.mapDepartment(original);

        } else {
            final Department saved = departmentRepository.save(existingDepartment);

            return customMapper.mapDepartment(saved);
        }
    }

    public DepartmentDTO deleteDepartment(final Integer departmentId) {

        final Department toDelete = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department does not exist!"));

        departmentRepository.delete(toDelete);

        return customMapper.mapDepartment(toDelete);
    }

    public DepartmentDTO getDepartmentById(final Integer departmentId) {

        final Department existingDepartment = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department does not exist!"));

        return customMapper.mapDepartment(existingDepartment);
    }

}
