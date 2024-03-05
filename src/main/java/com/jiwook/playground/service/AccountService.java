package com.jiwook.playground.service;

import com.jiwook.playground.dto.ResponseDTO;
import com.jiwook.playground.dto.request.LoginDTO;

public interface AccountService {
    void join();

    ResponseDTO login(LoginDTO loginDTO);
}