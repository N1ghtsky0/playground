package com.jiwook.playground.controller;

import com.jiwook.playground.dto.ResponseDTO;
import com.jiwook.playground.dto.reponse.TokenDTO;
import com.jiwook.playground.dto.request.LoginDTO;
import com.jiwook.playground.service.AccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        ResponseDTO loginResult = accountService.login(loginDTO);

        if (loginResult.getResult().equals("success")) {
            Cookie cookie = new Cookie("refresh_token", ((TokenDTO) loginResult.getData()).getRefreshToken());
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loginResult);
    }
}
