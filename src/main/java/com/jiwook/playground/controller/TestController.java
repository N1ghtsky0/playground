package com.jiwook.playground.controller;

import com.jiwook.playground.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {
    private final TestService testService;

    @GetMapping("/save")
    public ResponseEntity<?> saveSuccess() {
        testService.saveSuccessWithOutAnyProblem();
        return ResponseEntity.ok().build();
    }

    /* 컨트롤러 null + 서비스 null */
    @GetMapping("/fail-no-transactional")
    public ResponseEntity<?> ControllerNullServiceNoTransactional() {
        testService.saveFailWithOutTransactional();
        return ResponseEntity.ok().build();
    }

    /* 컨트롤러 null + 서비스 @Transactional */
    @GetMapping("/fail-transactional")
    public ResponseEntity<?> ControllerNullServiceTransactional() {
        testService.saveFailWithTransactional();
        return ResponseEntity.ok().build();
    }

    /* 컨트롤러 null + 서비스 try-catch */
    @GetMapping("/fail-try-catch")
    public ResponseEntity<?> ControllerNullServiceTryCatch() {
        testService.saveFailWithTryCatch();
        return ResponseEntity.ok().build();
    }

    /* 컨트롤러 try-catch + 서비스 null */
    @GetMapping("/try-catch/fail-no-transactional")
    public ResponseEntity<?> ControllerTryCatchServiceNoTransactional() {
        try {
            testService.saveFailWithOutTransactional();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    /* 컨트롤러 try-catch + 서비스 @Transactional */
    @GetMapping("/try-catch/fail-transactional")
    public ResponseEntity<?> ControllerTryCatchServiceTransactional() {
        try {
            testService.saveFailWithTransactional();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    /* 컨트롤러 try-catch + 서비스 try-catch */
    @GetMapping("/try-catch/fail-try-catch")
    public ResponseEntity<?> ControllerTryCatchServiceTryCatch() {
        try {
            testService.saveFailWithTryCatch();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    /* 컨트롤러 @Transactional + 서비스 null */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/transactional/fail-no-transactional")
    public ResponseEntity<?> ControllerTransactionalServiceNull() {
        testService.saveFailWithOutTransactional();
        return ResponseEntity.ok().build();
    }

    /* 컨트롤러 @Transactional + 서비스 @Transactional */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/transactional/fail-transactional")
    public ResponseEntity<?> ControllerTransactionalServiceTransactional() {
        testService.saveFailWithTransactional();
        return ResponseEntity.ok().build();
    }

    /* 컨트롤러 @Transactional + 서비스 try-catch */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/transactional/fail-try-catch")
    public ResponseEntity<?> ControllerTransactionalServiceTryCatch() {
        testService.saveFailWithTryCatch();
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(RuntimeException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
