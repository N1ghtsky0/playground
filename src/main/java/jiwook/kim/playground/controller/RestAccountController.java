package jiwook.kim.playground.controller;

import jiwook.kim.playground.dto.request.RequestLogIn;
import jiwook.kim.playground.dto.request.RequestSignUp;
import jiwook.kim.playground.dto.response.ResponseLogIn;
import jiwook.kim.playground.dto.response.ResponseMyInfo;
import jiwook.kim.playground.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestAccountController {
    private final AccountService accountService;

    @GetMapping("/join")
    public ResponseEntity<Boolean> checkJoinValueDuplicate(@RequestParam("name") String name,
                                                           @RequestParam("val") String val) {
        boolean result = false;
        if (name.equals("loginId")) {
            result = accountService.isLoginIdDuplicate(val);
        } else if (name.equals("nickName")) {
            result = accountService.isNickNameDuplicate(val);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @PostMapping("/join")
    public ResponseEntity<Boolean> signUp(@RequestBody RequestSignUp requestSignUp) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.SignUp(requestSignUp));
    }

    @GetMapping("/my")
    public ResponseEntity<ResponseMyInfo> getMyInfo(Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.getMyInfo(authentication.getName()));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseLogIn> logIn(@RequestBody RequestLogIn requestLogIn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.login(requestLogIn));
    }
}
