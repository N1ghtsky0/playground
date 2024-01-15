package com.jiwook.playground.controller;

import com.jiwook.playground.dto.FormData;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/form/servlet-request")
    public String formByHttpServletRequest(HttpServletRequest httpServletRequest) {
        String name = httpServletRequest.getParameter("name");
        String fruit = httpServletRequest.getParameter("fruit");

        log.info("formByHttpServletRequest");
        log.info(String.format("form-data ===> {name=%s, fruit=%s}", name, fruit));

        return "redirect:/";
    }

    @PostMapping("/form/request-params")
    public String formByRequestParams(@RequestParam(value = "name") String name,
                                      @RequestParam(value = "fruit") String fruit) {
        log.info("formByRequestParams");
        log.info(String.format("form-data ===> {name=%s, fruit=%s}", name, fruit));

        return "redirect:/";
    }
}
