package jiwook.kim.playground.base;

import jakarta.annotation.PostConstruct;
import jiwook.kim.playground.Entity.Account;
import jiwook.kim.playground.dto.request.RequestSignUp;
import jiwook.kim.playground.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitializer {
    private final AccountRepo accountRepo;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void initAdminAccount() {
        RequestSignUp requestSignUp = new RequestSignUp();
        requestSignUp.setName("관리자");
        requestSignUp.setLoginId("admin");
        requestSignUp.setLoginPwd("admin");
        requestSignUp.setNickName("관리자");
        requestSignUp.setBirthDay("19700101");

        if (!accountRepo.existsAccountByLoginId(requestSignUp.getLoginId())) {
            accountRepo.save(Account.createAdmin(requestSignUp, passwordEncoder));
        }
    }
}
