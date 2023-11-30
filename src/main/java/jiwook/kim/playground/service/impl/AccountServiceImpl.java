package jiwook.kim.playground.service.impl;

import jiwook.kim.playground.Entity.Account;
import jiwook.kim.playground.dto.request.RequestSignUp;
import jiwook.kim.playground.repository.AccountRepo;
import jiwook.kim.playground.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;

    @Override
    public boolean SignUp(RequestSignUp requestSignUp) {
        if (isLoginIdDuplicate(requestSignUp.getLoginId()) || isNickNameDuplicate(requestSignUp.getNickName())) {
            return false;
        }

        accountRepo.save(Account.createUser(requestSignUp));
        return true;
    }

    @Override
    public boolean isLoginIdDuplicate(String loginId) {
        return accountRepo.existsAccountByLoginId(loginId);
    }

    @Override
    public boolean isNickNameDuplicate(String nickName) {
        return accountRepo.existsAccountByNickName(nickName);
    }
}
