package kim.jiwook.playground.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class MainController {

    /**
     * 메인 페이지
     *
     * @param httpServletRequest HttpServletRequest
     * @return /
     */
    @RequestMapping("/")
    public String mainPage(HttpServletRequest httpServletRequest) {
        log.info(httpServletRequest.getRequestURI());
        return "/index";
    }
}
