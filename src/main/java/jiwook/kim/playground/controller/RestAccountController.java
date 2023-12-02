package jiwook.kim.playground.controller;

import jiwook.kim.playground.dto.ApiResponse;
import jiwook.kim.playground.dto.request.RequestLogIn;
import jiwook.kim.playground.dto.request.RequestSignUp;
import jiwook.kim.playground.dto.request.RequestUpdatePassword;
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
    public ResponseEntity<ApiResponse> checkJoinValueDuplicate(@RequestParam("name") String name,
                                                           @RequestParam("val") String val) {
        boolean result = false;
        if (name.equals("loginId")) {
            result = accountService.isLoginIdDuplicate(val);
        } else if (name.equals("nickName")) {
            result = accountService.isNickNameDuplicate(val);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(result));
    }

    @PostMapping("/join")
    public ResponseEntity<ApiResponse> signUp(@RequestBody RequestSignUp requestSignUp) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(accountService.SignUp(requestSignUp)));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse> getMyInfo(Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(accountService.getMyInfo(authentication.getName())));
    }

    @PostMapping("/my")
    public ResponseEntity<ApiResponse> updateLoginPwd(@RequestBody RequestUpdatePassword requestUpdatePassword, Authentication authentication) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(accountService.updatePassword(requestUpdatePassword, authentication.getName())));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> logIn(@RequestBody RequestLogIn requestLogIn) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(accountService.login(requestLogIn)));
    }
}
