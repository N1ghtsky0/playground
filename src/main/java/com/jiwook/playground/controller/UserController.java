package com.jiwook.playground.controller;

import com.jiwook.playground.dto.RequestJoin;
import com.jiwook.playground.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/valid/id")
    @ResponseBody
    public ResponseEntity<Boolean> isLoginIdDuplicate(@RequestParam("loginId") String loginId) {
        return ResponseEntity.ok(userService.isLoginIdDuplicate(loginId));
    }

    @PostMapping("/join")
    public String joinProcess(@ModelAttribute RequestJoin requestDTO) {
        userService.joinUser(requestDTO);
        return "/index";
    }
}
