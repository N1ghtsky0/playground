package com.jiwook.playground.controller;

import com.jiwook.playground.dto.FormData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Slf4j
@Controller
public class FormController {

    @PostMapping("/form/hash-map")
    public String formByHashMap(@RequestParam HashMap<String, Object> formData) {
        log.info("formByHashMap");
        log.info("form-data ===> " + formData);

        return "redirect:/";
    }

    @PostMapping("/form/dto")
    public String formByDTO(FormData formData) {
        log.info("formByDTO");
        log.info("form-data ===> " + formData.toString());

        return "redirect:/";
    }
}
