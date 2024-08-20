package com.jiwook.playground;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping(value = {"/", "/index"})
    public String defaultIndex() {
        return "defaultPage/index.html";
    }

    @RequestMapping(value = {"/fragmentIndex"})
    public String fragmentIndex() {
        return "fragmentPage/index.html";
    }

    @RequestMapping(value = {"/layoutIndex"})
    public String layoutIndex() {
        return "layoutPage/index.html";
    }
}
