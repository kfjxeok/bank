package com.junseok.bankservice.repository;


import com.junseok.bankservice.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer> {
    TestEntity findById(int id);
    void deleteById(int id);
}
