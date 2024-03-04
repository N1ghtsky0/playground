package com.jiwook.playground.controller;

import com.jiwook.playground.entity.User;
import com.jiwook.playground.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/")
    public String indexPage(Model model, Authentication authentication) {
        if (authentication != null) {
            model.addAttribute("loginYN", true);
            model.addAttribute("account", ((User) authentication.getPrincipal()).getUsername());
        }
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/join")
    @ResponseBody
    public ResponseEntity<?> joinUser() {
        userService.join();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
