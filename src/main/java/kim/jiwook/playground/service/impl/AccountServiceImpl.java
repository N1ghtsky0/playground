package kim.jiwook.playground.service.impl;

import kim.jiwook.playground.Entity.Account;
import kim.jiwook.playground.configuration.JsonWebTokenProvider;
import kim.jiwook.playground.configuration.exception.CustomException;
import kim.jiwook.playground.repository.AccountRepo;
import kim.jiwook.playground.service.AccountService;
import kim.jiwook.playground.vo.request.RequestLogIn;
import kim.jiwook.playground.vo.request.RequestSignUp;
import kim.jiwook.playground.vo.response.ResponseLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static kim.jiwook.playground.configuration.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final PasswordEncoder encoder;
    private final JsonWebTokenProvider tokenProvider;

    @Override
    public boolean isLoginIdDuplicate(String loginId) {
        return accountRepo.findAccountByLoginId(loginId).isPresent();
    }

    @Override
    public void createAccount(RequestSignUp request) {
        if (isLoginIdDuplicate(request.getLoginId())) {
            throw new CustomException(DUPLICATED_LOGIN_ID);
        }
        accountRepo.save(Account.from(request, encoder));
    }

    @Override
    public ResponseLogin logInProcess(RequestLogIn request) {
        Account account = accountRepo.findAccountByLoginId(request.getLoginId())
                .filter(it -> encoder.matches(request.getLoginPwd(), it.getLoginPwd()))
                .orElseThrow(() -> new CustomException(BAD_REQUEST_LOGIN));
        String token = tokenProvider.createToken(account.getUuid());

        return ResponseLogin.builder()
                .token(token)
                .build();
    }
}
