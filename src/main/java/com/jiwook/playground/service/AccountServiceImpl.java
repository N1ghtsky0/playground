package com.jiwook.playground.service;

import com.jiwook.playground.dto.ResponseDTO;
import com.jiwook.playground.dto.request.LoginDTO;
import com.jiwook.playground.entity.Account;
import com.jiwook.playground.repository.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join() {
        String accountIndex = String.valueOf(accountRepo.count());
        accountRepo.save(Account.builder()
                .username("test" + accountIndex)
                .password(passwordEncoder.encode("password123"))
                .build());
    }

    @Override
    public ResponseDTO login(LoginDTO loginDTO) {
        Account account = accountRepo.findByUsername(loginDTO.getUsername()).orElse(null);
        if (account != null && passwordEncoder.matches(loginDTO.getPassword(), account.getPassword())) {
            //todo Token 생성
            return ResponseDTO.success(null);
        }
        return ResponseDTO.fail("아이디 또는 비밀번호가 틀렸습니다.");
    }
}