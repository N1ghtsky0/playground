package com.jiwook.playground.repository;

import com.jiwook.playground.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<TestEntity, Integer> {
}
