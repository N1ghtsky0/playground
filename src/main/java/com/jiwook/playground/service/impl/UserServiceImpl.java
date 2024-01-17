package com.jiwook.playground.service.impl;

import com.jiwook.playground.dto.RequestJoin;
import com.jiwook.playground.model.User;
import com.jiwook.playground.model.UserRole;
import com.jiwook.playground.repository.UserRepo;
import com.jiwook.playground.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isLoginIdDuplicate(String loginId) {
        return userRepo.existsByLoginId(loginId);
    }

    @Override
    public void joinUser(RequestJoin requestDTO) {
        userRepo.save(User.builder()
                .loginId(requestDTO.getLoginId())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .role(UserRole.USER)
                .build());
    }
}
