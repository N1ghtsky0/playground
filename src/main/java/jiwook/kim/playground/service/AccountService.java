package jiwook.kim.playground.service;

import jiwook.kim.playground.dto.request.RequestSignUp;

public interface AccountService {

    boolean SignUp(RequestSignUp requestSignUp);

    boolean isLoginIdDuplicate(String loginId);

    boolean isNickNameDuplicate(String nickName);

}
