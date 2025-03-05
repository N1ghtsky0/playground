package com.jiwook.playground.controller;

import com.jiwook.playground.entity.TestEntity;
import com.jiwook.playground.repository.TestRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
class TestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TestRepo testRepo;

    @BeforeEach
    void clearDB() {
        testRepo.deleteAll();
    }

    @Test
    @DisplayName("저장 성공")
    void saveSuccess() throws Exception {
        mockMvc.perform(get("/save"))
                .andExpect(status().isOk());

        List<TestEntity> testEntities = testRepo.findAll();
        assertFalse(testEntities.isEmpty());
        log.info(testEntities.get(0).getName());
    }

    @Test
    @DisplayName("컨트롤러 X, 서비스 X")
    void fail_1_1() throws Exception {
        mockMvc.perform(get("/fail-no-transactional"))
                .andExpect(status().isInternalServerError());

        List<TestEntity> testEntities = testRepo.findAll();
        assertFalse(testEntities.isEmpty());
        log.info(testEntities.get(0).getName());
    }

    @Test
    @DisplayName("컨트롤러 X, 서비스 Transactional")
    void fail_1_2() throws Exception {
        mockMvc.perform(get("/fail-transactional"))
                .andExpect(status().isInternalServerError());

        List<TestEntity> testEntities = testRepo.findAll();
        assertTrue(testEntities.isEmpty());
    }

    @Test
    @DisplayName("컨트롤러 X, 서비스 try-catch")
    void fail_1_3() throws Exception {
        mockMvc.perform(get("/fail-try-catch"))
                .andExpect(status().isOk());

        List<TestEntity> testEntities = testRepo.findAll();
        assertFalse(testEntities.isEmpty());
        log.info(testEntities.get(0).getName());
    }

    @Test
    @DisplayName("컨트롤러 try-catch, 서비스 X")
    void fail_2_1() throws Exception {
        mockMvc.perform(get("/try-catch/fail-no-transactional"))
                .andExpect(status().isOk());

        List<TestEntity> testEntities = testRepo.findAll();
        assertFalse(testEntities.isEmpty());
        log.info(testEntities.get(0).getName());
    }

    @Test
    @DisplayName("컨트롤러 try-catch, 서비스 Transactional")
    void fail_2_2() throws Exception {
        mockMvc.perform(get("/try-catch/fail-transactional"))
                .andExpect(status().isOk());

        List<TestEntity> testEntities = testRepo.findAll();
        assertTrue(testEntities.isEmpty());
    }

    @Test
    @DisplayName("컨트롤러 try-catch, 서비스 try-catch")
    void fail_2_3() throws Exception {
        mockMvc.perform(get("/try-catch/fail-try-catch"))
                .andExpect(status().isOk());

        List<TestEntity> testEntities = testRepo.findAll();
        assertFalse(testEntities.isEmpty());
        log.info(testEntities.get(0).getName());
    }

    @Test
    @DisplayName("컨트롤러 transactional, 서비스 X")
    void fail_3_1() throws Exception {
        mockMvc.perform(get("/transactional/fail-no-transactional"))
                .andExpect(status().isInternalServerError());

        List<TestEntity> testEntities = testRepo.findAll();
        assertTrue(testEntities.isEmpty());
    }

    @Test
    @DisplayName("컨트롤러 transactional, 서비스 transactional")
    void fail_3_2() throws Exception {
        mockMvc.perform(get("/transactional/fail-transactional"))
                .andExpect(status().isInternalServerError());

        List<TestEntity> testEntities = testRepo.findAll();
        assertTrue(testEntities.isEmpty());
    }

    @Test
    @DisplayName("컨트롤러 transactional, 서비스 try-catch")
    void fail_3_3() throws Exception {
        mockMvc.perform(get("/transactional/fail-try-catch"))
                .andExpect(status().isOk());

        List<TestEntity> testEntities = testRepo.findAll();
        assertFalse(testEntities.isEmpty());
        log.info(testEntities.get(0).getName());
    }
}