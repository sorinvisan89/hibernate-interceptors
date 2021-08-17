package com.playground.demo.repository;

import com.playground.demo.entity.Audit;
import org.springframework.data.repository.CrudRepository;

public interface AuditRepository extends CrudRepository<Audit, Integer> {
}
