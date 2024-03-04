package com.jiwook.playground.service;

import com.jiwook.playground.entity.User;
import com.jiwook.playground.repository.UserRepo;
import com.jiwook.playground.util.UserRole;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.")
        );
    }

    @Override
    public void join() {
        final String userIndex = String.valueOf(userRepo.count());
        userRepo.save(User.builder()
                .username("test" + userIndex)
                .password(passwordEncoder.encode("afsd1423!"))
                .role(UserRole.USER)
                .build());
    }
}
