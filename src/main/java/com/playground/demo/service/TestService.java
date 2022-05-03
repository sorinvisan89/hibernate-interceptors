package com.playground.demo.service;

import com.playground.demo.entity.TestEntity;
import com.playground.demo.event.CustomEvent;
import com.playground.demo.mapper.CustomMapper;
import com.playground.demo.model.TestDTO;
import com.playground.demo.model.TestRequestDTO;
import com.playground.demo.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

    private final TestRepository testRepository;
    private final CustomMapper customMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public TestService(final TestRepository testRepository, final CustomMapper customMapper, final ApplicationEventPublisher applicationEventPublisher){
        this.testRepository = testRepository;
        this.customMapper = customMapper;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public TestDTO addTest(final TestRequestDTO testRequestDTO) {
        final TestEntity toSave = new TestEntity();
        toSave.setName(testRequestDTO.getName());

        final TestEntity saved = testRepository.save(toSave);
        final CustomEvent customEvent = new CustomEvent(this, saved.getId(), saved.getName());

        applicationEventPublisher.publishEvent(customEvent);
        return customMapper.mapTest(saved);
    }


}
