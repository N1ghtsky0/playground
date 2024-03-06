package com.jiwook.playground.controller;

import com.jiwook.playground.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {
    @GetMapping("/test/get")
    public ResponseEntity<ResponseDTO> testGet() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.success("test get success!!"));
    }
}
