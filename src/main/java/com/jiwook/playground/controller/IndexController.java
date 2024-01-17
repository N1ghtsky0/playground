package com.jiwook.playground.controller;

import com.jiwook.playground.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final UserService userService;

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

    @GetMapping(value = "/info")
    public String myInfoPage(Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUserInfoByLoginId(authentication.getName()));
        return "/my-info";
    }

    @GetMapping(value = "/users")
    public String allUserInfoPage(Model model) {
        model.addAttribute("userList", userService.getAllUser());
        return "/all-user";
    }
}
