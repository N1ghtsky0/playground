package com.jiwook.playground.controller;

import com.jiwook.playground.entity.MainEntity;
import com.jiwook.playground.repository.MainRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/main")
@RestController
public class MainController {
    private final MainRepo mainRepo;

    @GetMapping
    public ResponseEntity<List<MainEntity>> getAllMainEntity() {
        log.info("메인 엔티티 전체 조회 호출");
        return ResponseEntity.ok(mainRepo.findAll());
    }

    @GetMapping("/{seq}")
    public ResponseEntity<MainEntity> getMainEntity(@PathVariable("seq") Long seq) {
        log.info("메인 엔티티 개별 조회 호출");
        return ResponseEntity.ok(mainRepo.findBySeq(seq));
    }

    @PostMapping
    public ResponseEntity<?> saveMainEntity(@RequestBody HashMap<String, String> hashMap) {
        log.info("메인 엔티티 저장 호출");
        mainRepo.save(MainEntity.builder()
                .name(hashMap.get("name"))
                .build());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
