package com.playground.demo.repository;

import com.playground.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e JOIN FETCH e.parentDepartment")
    List<Employee> fetchEmployees();
}
