package com.jiwook.playground.service;

import com.jiwook.playground.entity.TestEntity;
import com.jiwook.playground.repository.TestRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class TestService {
    private final TestRepo testRepo;

    public void saveSuccessWithOutAnyProblem() {
        testRepo.save(new TestEntity("saveSuccessWithOutAnyProblem"));
    }

    public void saveFailWithOutTransactional() {
        testRepo.save(new TestEntity("saveFailWithOutTransactional"));
        throw new RuntimeException("saveFailWithOutTransactional error");
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveFailWithTransactional() {
        testRepo.save(new TestEntity("saveFailWithTransactional"));
        throw new RuntimeException("saveFailWithTransactional error");
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveFailWithTryCatch() {
        try {
            testRepo.save(new TestEntity("saveFailWithTryCatch"));
            throw new RuntimeException("saveFailWithTryCatch error");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
