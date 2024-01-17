package com.jiwook.playground.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value = "/")
    public String indexPage(Authentication authentication, Model model) {
        if (authentication != null) {
            model.addAttribute("nickName", authentication.getName());
        }
        return "/index";
    }

    @GetMapping(value = "/join")
    public String joinPage() {
        return "/join";
    }

    @GetMapping(value = "/login")
    public String loginPage(Authentication authentication) {
        if (authentication == null) {
            return "/login";
        } else {
            return "redirect:/";
        }
    }
}
