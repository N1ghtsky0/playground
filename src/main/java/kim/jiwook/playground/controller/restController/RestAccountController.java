package kim.jiwook.playground.controller.restController;

import kim.jiwook.playground.service.AccountService;
import kim.jiwook.playground.vo.request.RequestLogIn;
import kim.jiwook.playground.vo.request.RequestSignUp;
import kim.jiwook.playground.vo.response.ResponseLogin;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<ResponseLogin> restfulLogin(@Valid @RequestBody RequestLogIn request) {
        return ResponseEntity.ok(accountService.logInProcess(request));
    }
}
