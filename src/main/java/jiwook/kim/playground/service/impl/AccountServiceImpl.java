package jiwook.kim.playground.service.impl;

import jiwook.kim.playground.Entity.Account;
import jiwook.kim.playground.Entity.RefreshToken;
import jiwook.kim.playground.base.common.TokenType;
import jiwook.kim.playground.base.config.TokenProvider;
import jiwook.kim.playground.dto.request.RequestLogIn;
import jiwook.kim.playground.dto.request.RequestSignUp;
import jiwook.kim.playground.dto.response.ResponseLogIn;
import jiwook.kim.playground.dto.response.ResponseMyInfo;
import jiwook.kim.playground.repository.AccountRepo;
import jiwook.kim.playground.repository.RefreshTokenRepo;
import jiwook.kim.playground.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final PasswordEncoder encoder;
    private final AccountRepo accountRepo;
    private final RefreshTokenRepo refreshTokenRepo;
    private final TokenProvider tokenProvider;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public boolean SignUp(RequestSignUp requestSignUp) {
        if (isLoginIdDuplicate(requestSignUp.getLoginId()) || isNickNameDuplicate(requestSignUp.getNickName())) {
            return false;
        }

        accountRepo.save(Account.createUser(requestSignUp, encoder));
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
    public ResponseMyInfo getMyInfo(String uuid) {
        Account account = accountRepo.findAccountByUuid(uuid).orElse(null);
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

    @Override
    public ResponseLogIn login(RequestLogIn requestLogIn) {
        Account account = accountRepo.findAccountByLoginId(requestLogIn.getLoginId())
                .filter(it -> encoder.matches(requestLogIn.getLoginPwd(), it.getLoginPwd()))
                .orElse(null);

        if (account == null) {
            return null;
        }

        String accessToken = tokenProvider.createToken(String.format("%s:%s", account.getNickName(), account.getUuid()), TokenType.ACCESS);
        String refreshToken = tokenProvider.createToken("", TokenType.REFRESH); // 리프레시 토큰은 유저정보 필요 X

        Optional<RefreshToken> originRefreshToken = refreshTokenRepo.findRefreshTokenById(account.getUuid());
        originRefreshToken.ifPresent(refreshTokenRepo::delete); // 로그인 정보가 있는 상태에서 재로그인 시 기존 정보 삭제

        refreshTokenRepo.save(new RefreshToken(account.getUuid(), refreshToken, accessToken));

        return ResponseLogIn.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
