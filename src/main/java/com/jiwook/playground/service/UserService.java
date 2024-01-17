package com.jiwook.playground.service;

import com.jiwook.playground.dto.RequestJoin;

public interface UserService {
    boolean isLoginIdDuplicate(String loginId);

    void joinUser(RequestJoin requestDTO);
}
