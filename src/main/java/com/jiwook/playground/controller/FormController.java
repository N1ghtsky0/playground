package com.jiwook.playground.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Slf4j
@Controller
public class FormController {

    @PostMapping("/form")
    public String formIndex(@RequestParam HashMap<String, Object> formData) {
        log.info("form-data ===> " + formData);

        return "redirect:/";
    }
}
