package com.playground.demo.counter;

import com.playground.demo.model.DepartmentDTO;
import com.playground.demo.model.DepartmentRequestDTO;
import com.playground.demo.model.EmployeeRequestDTO;
import com.playground.demo.service.DepartmentService;
import com.playground.demo.service.EmployeeService;
import com.playground.demo.utils.SQLStatementCountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@ActiveProfiles("test")
@SpringBootTest
public class DepartmentTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        DepartmentRequestDTO dep1 = new DepartmentRequestDTO();
        dep1.setName("it");

        DepartmentRequestDTO dep2 = new DepartmentRequestDTO();
        dep2.setName("hr");

        DepartmentRequestDTO dep3 = new DepartmentRequestDTO();
        dep3.setName("finance");

        final DepartmentDTO itDepartment = departmentService.addDepartment(dep1);
        final DepartmentDTO hrDepartment = departmentService.addDepartment(dep2);
        final DepartmentDTO financeDepartment = departmentService.addDepartment(dep3);

        for (int i = 0; i < 10; i++) {
            final EmployeeRequestDTO hr = new EmployeeRequestDTO();
            hr.setDepartmentId(hrDepartment.getId());
            hr.setSalary(BigDecimal.valueOf(2000));
            hr.setName("Hr" + i);
            employeeService.addEmployee(hr);
        }

        for (int i = 0; i < 7; i++) {
            final EmployeeRequestDTO finance = new EmployeeRequestDTO();
            finance.setDepartmentId(financeDepartment.getId());
            finance.setSalary(BigDecimal.valueOf(2500));
            finance.setName("Finance" + i);
            employeeService.addEmployee(finance);
        }

        for (int i = 0; i < 50; i++) {
            final EmployeeRequestDTO it = new EmployeeRequestDTO();
            it.setDepartmentId(itDepartment.getId());
            it.setSalary(BigDecimal.valueOf(3500));
            it.setName("It" + i);
            employeeService.addEmployee(it);
        }

        SQLStatementCountValidator.reset();
    }

    @Test
    public void givenDepartmentsAndEmployees_whenFetchingDepartments_shouldReturnExpected() {

        SQLStatementCountValidator.reset();

        departmentService.fetchDepartments();

        SQLStatementCountValidator.assertSelectCount(1);
    }

    @Test
    public void givenDepartmentsAndEmployees_whenFindAllDepartments_shouldReturnExpected() {

        SQLStatementCountValidator.reset();

        departmentService.findDepartments();

        SQLStatementCountValidator.assertSelectCount(4);
    }

}
