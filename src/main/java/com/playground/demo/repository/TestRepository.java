package com.playground.demo.repository;

import com.playground.demo.entity.Department;
import com.playground.demo.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, String> {
}
