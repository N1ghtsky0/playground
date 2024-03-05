package com.jiwook.playground.controller;

import com.jiwook.playground.dto.ResponseDTO;
import com.jiwook.playground.dto.request.LoginDTO;
import com.jiwook.playground.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/auth/join")
    public ResponseEntity<ResponseDTO> join() {
        accountService.join();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDTO.success(null));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.login(loginDTO));
    }
}
