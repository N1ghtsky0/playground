package com.jiwook.playground.controller;

import com.jiwook.playground.service.AService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AController {
    private final AService aService;

    @GetMapping("/a")
    public String a() {
        aService.serviceMethod();
        return "index";
    }
}
