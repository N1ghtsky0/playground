package com.jiwook.playground.controller;

import com.jiwook.playground.entity.MainEntity;
import com.jiwook.playground.repository.MainRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/main")
@RestController
public class MainController {
    private final MainRepo mainRepo;

    @GetMapping
    public ResponseEntity<List<MainEntity>> getAllMainEntity() {
        return ResponseEntity.ok(mainRepo.findAll());
    }

    @GetMapping("/{seq}")
    public ResponseEntity<MainEntity> getMainEntity(@PathVariable("seq") Long seq) {
        return ResponseEntity.ok(mainRepo.findBySeq(seq));
    }

    @PostMapping
    public ResponseEntity<?> saveMainEntity(@RequestBody HashMap<String, String> hashMap) {
        mainRepo.save(MainEntity.builder()
                .name(hashMap.get("name"))
                .build());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
