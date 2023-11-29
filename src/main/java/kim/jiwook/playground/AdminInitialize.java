package kim.jiwook.playground;

import kim.jiwook.playground.Entity.Account;
import kim.jiwook.playground.repository.AccountRepo;
import kim.jiwook.playground.vo.request.RequestSignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class AdminInitialize {

    private final AccountRepo accountRepo;
    private final PasswordEncoder encoder;

    @PostConstruct
    private void createAdminAccount() {
        RequestSignUp accountInfo = new RequestSignUp();
        accountInfo.setLoginId("admin1234");
        accountInfo.setLoginPwd("admin1234");

        if (!accountRepo.findAccountByLoginId(accountInfo.getLoginId()).isPresent()) {
            accountRepo.save(Account.adminFrom(accountInfo, encoder));
        }
    }
}
