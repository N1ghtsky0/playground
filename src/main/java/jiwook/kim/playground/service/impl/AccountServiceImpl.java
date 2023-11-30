package jiwook.kim.playground.service.impl;

import jiwook.kim.playground.Entity.Account;
import jiwook.kim.playground.dto.request.RequestSignUp;
import jiwook.kim.playground.dto.response.ResponseMyInfo;
import jiwook.kim.playground.repository.AccountRepo;
import jiwook.kim.playground.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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

    @Override
    public ResponseMyInfo getMyInfo(Long seq) {
        Account account = accountRepo.findAccountBySeq(seq).orElse(null);
        if (account == null) {
            return null;
        }

        return ResponseMyInfo.builder()
                .loginId(account.getLoginId())
                .name(account.getName())
                .birthDay(account.getBirthDay())
                .nickName(account.getNickName())
                .createDate(account.getCreateDate().format(dateTimeFormatter))
                .build();
    }

}
