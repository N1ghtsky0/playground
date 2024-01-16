package com.jiwook.playground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = "/")
    public String indexPage() {
        return "/index";
    }

    @GetMapping(value = "/join")
    public String joinPage() {
        return "/join";
    }
}
