package kim.jiwook.playground.service.impl;

import kim.jiwook.playground.Entity.Account;
import kim.jiwook.playground.configuration.exception.CustomException;
import kim.jiwook.playground.repository.AccountRepo;
import kim.jiwook.playground.service.AccountService;
import kim.jiwook.playground.vo.request.RequestSignIn;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static kim.jiwook.playground.configuration.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final PasswordEncoder encoder;

    @Override
    public boolean isLoginIdDuplicate(String loginId) {
        return accountRepo.findAccountByLoginId(loginId).isPresent();
    }

    @Override
    public void createAccount(RequestSignIn request) {
        if (isLoginIdDuplicate(request.getLoginId())) {
            throw new CustomException(DUPLICATED_LOGIN_ID);
        }
        accountRepo.save(Account.from(request, encoder));
    }
}
