package com.jiwook.playground.service;

import com.jiwook.playground.dto.RequestJoin;
import com.jiwook.playground.model.User;

public interface UserService {
    boolean isLoginIdDuplicate(String loginId);

    void joinUser(RequestJoin requestDTO);

    User getUserInfoByLoginId(String loginId);
}
