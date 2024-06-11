package com.jiwook.playground.controller;

import com.jiwook.playground.service.BService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BController {
    private final BService bService;

    @GetMapping("/b")
    public String b() {
        bService.ServiceMethod();
        return "index";
    }
}
