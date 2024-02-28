package com.jiwook.playground.controller;

import com.jiwook.playground.dto.SubDTO;
import com.jiwook.playground.entity.SubEagerEntity;
import com.jiwook.playground.entity.SubLazyEntity;
import com.jiwook.playground.repository.MainRepo;
import com.jiwook.playground.repository.SubEagerRepo;
import com.jiwook.playground.repository.SubLazyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/sub")
@RestController
public class SubController {
    private final SubEagerRepo subEagerRepo;
    private final SubLazyRepo subLazyRepo;
    private final MainRepo mainRepo;

    @GetMapping("/all")
    public ResponseEntity<List<?>> getAllSubEntity(@RequestParam("flag") String flag) {
        if ("e".equals(flag)) {
            return ResponseEntity.ok(subEagerRepo.findAll());
        } else if ("l".equals(flag)) {
            subLazyRepo.findAll();
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("/item/{seq}")
    public ResponseEntity<?> getSubEntity(@PathVariable("seq") Long seq, @RequestParam("flag") String flag) {
        if ("e".equals(flag)) {
            return ResponseEntity.ok(subEagerRepo.findBySeq(seq));
        } else if ("l".equals(flag)) {
            subLazyRepo.findBySeq(seq);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> saveSubEntity(@RequestBody HashMap<String, String> hashMap) {
        if ("e".equals(hashMap.get("flag"))) {
            subEagerRepo.save(SubEagerEntity.builder()
                    .name(hashMap.get("name"))
                    .entity(mainRepo.findBySeq(Long.parseLong(hashMap.get("seq"))))
                    .build());
        } else if ("l".equals(hashMap.get("flag"))) {
            subLazyRepo.save(SubLazyEntity.builder()
                    .name(hashMap.get("name"))
                    .entity(mainRepo.findBySeq(Long.parseLong(hashMap.get("seq"))))
                    .build());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/lazy/test/{seq}")
    public ResponseEntity<?> lazyTest(@PathVariable("seq") Long seq, @RequestParam("flag") String flag) {
        SubLazyEntity lazyEntity = subLazyRepo.findBySeq(seq);
        if ("t".equals(flag)) {
            return ResponseEntity.ok(new SubDTO(lazyEntity));
        }
        return ResponseEntity.ok().build();
    }
}
