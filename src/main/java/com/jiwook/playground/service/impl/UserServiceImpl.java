package com.jiwook.playground.service.impl;

import com.jiwook.playground.repository.UserRepo;
import com.jiwook.playground.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
}
