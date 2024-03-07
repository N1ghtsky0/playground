package com.jiwook.playground.service;

import com.jiwook.playground.dto.ResponseDTO;
import com.jiwook.playground.dto.reponse.TokenDTO;
import com.jiwook.playground.dto.request.LoginDTO;
import com.jiwook.playground.entity.Account;
import com.jiwook.playground.repository.AccountRepo;
import com.jiwook.playground.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join() {
        String accountIndex = String.valueOf(accountRepo.count());
        accountRepo.save(Account.builder()
                .username("test" + accountIndex)
                .password(passwordEncoder.encode("password123"))
                .build());
    }

    /**
     * 로그인
     * @param loginDTO 로그인 아이디, 비밀번호
     * @return 성공 시 - success(TokenDTO), 실패 시 - fail
     */
    @Override
    public ResponseDTO login(LoginDTO loginDTO) {
        Account account = accountRepo.findByUsername(loginDTO.getUsername()).orElse(null);
        if (account != null && passwordEncoder.matches(loginDTO.getPassword(), account.getPassword())) {
            final String accessToken = tokenProvider.createAccessToken(account.getUsername());
            final String refreshToken = tokenProvider.createRefreshToken(accessToken);
            return ResponseDTO.success(TokenDTO.builder().accessToken(accessToken).refreshToken(refreshToken).build());
        }
        return ResponseDTO.fail("아이디 또는 비밀번호가 틀렸습니다.");
    }
}