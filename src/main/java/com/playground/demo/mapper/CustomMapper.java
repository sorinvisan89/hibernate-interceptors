package com.playground.demo.mapper;

import com.playground.demo.entity.Department;
import com.playground.demo.entity.Employee;
import com.playground.demo.model.DepartmentDTO;
import com.playground.demo.model.EmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface CustomMapper {

    @Mappings({
            @Mapping(target = "id", source = "departmentId"),
            @Mapping(target = "name", source = "departmentName")
    })
    DepartmentDTO mapDepartment(final Department entity);

    List<DepartmentDTO> mapDepartments(final List<Department> departments);

    @Mappings({
            @Mapping(target = "id", source = "employeeId"),
            @Mapping(target = "departmentName", source = "parentDepartment.departmentName"),
            @Mapping(target = "departmentId", source = "parentDepartment.departmentId"),
//            @Mapping(target = "salary", ignore = true)
    })
    EmployeeDTO mapEmployee(final Employee employee);

    List<EmployeeDTO> mapEmployees(final List<Employee> employees);

}
