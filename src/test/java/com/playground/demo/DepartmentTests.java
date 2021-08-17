package com.playground.demo;

import com.playground.demo.model.DepartmentDTO;
import com.playground.demo.service.DepartmentService;
import com.playground.demo.utils.SQLStatementCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class DepartmentTests {

    @Autowired
    private DepartmentService departmentService;

    public DepartmentTests() {
    }

    @BeforeEach
    public void setup() {
//        interceptor.clearCounter();
        SQLStatementCounter.reset();
    }

    @Test
    public void test1() {
        List<DepartmentDTO> departmentList = departmentService.findDepartments();
//        interceptor.getQueryCount();
        SQLStatementCounter.assertSelectCount(1);
    }
}
