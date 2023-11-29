package kim.jiwook.playground.controller.restController;

import kim.jiwook.playground.service.AccountService;
import kim.jiwook.playground.vo.request.RequestLogIn;
import kim.jiwook.playground.vo.request.RequestSignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RestAccountController {
    private final AccountService accountService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> restfulSignUp(@Valid @RequestBody RequestSignUp request) {
        accountService.createAccount(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> restfulLogin(@Valid @RequestBody RequestLogIn request) {
        ResponseCookie cookie = ResponseCookie.from("jwt", accountService.logInProcess(request).getToken())
                .httpOnly(true)
                .path("/")
                .maxAge(60 * 60 * 24)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}
