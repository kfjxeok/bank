package com.junseok.bankservice.service;


import com.junseok.bankservice.dto.TestDTO;
import com.junseok.bankservice.entity.TestEntity;
import com.junseok.bankservice.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public Iterable<TestEntity> getAllBoard() {
        return testRepository.findAll();
    }

    public TestEntity setBoard(TestDTO testDto){
        TestEntity testEntity = new TestEntity();
        testEntity.setContent(testDto.getContent());
        testEntity.setTitle(testDto.getTitle());
        return testRepository.save(testEntity);
    }
}