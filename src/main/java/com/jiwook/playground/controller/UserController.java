package com.jiwook.playground.controller;

import com.jiwook.playground.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
}
