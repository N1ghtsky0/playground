package com.jiwook.playground.service.impl;

import com.jiwook.playground.dto.RequestJoin;
import com.jiwook.playground.model.User;
import com.jiwook.playground.model.UserRole;
import com.jiwook.playground.repository.UserRepo;
import com.jiwook.playground.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
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
                .nickName(requestDTO.getLoginId())
                .role(UserRole.USER)
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
    }
}
