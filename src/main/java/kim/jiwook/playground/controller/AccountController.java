package kim.jiwook.playground.controller;

import kim.jiwook.playground.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest httpServletRequest) {
        log.info(httpServletRequest.getRequestURI());
        return "/account/login";
    }
}
