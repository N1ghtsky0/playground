package jiwook.kim.playground.service;

import jiwook.kim.playground.dto.request.RequestLogIn;
import jiwook.kim.playground.dto.request.RequestSignUp;
import jiwook.kim.playground.dto.request.RequestUpdatePassword;
import jiwook.kim.playground.dto.response.ResponseLogIn;
import jiwook.kim.playground.dto.response.ResponseMyInfo;

public interface AccountService {

    boolean SignUp(RequestSignUp requestSignUp);

    boolean isLoginIdDuplicate(String loginId);

    boolean isNickNameDuplicate(String nickName);

    ResponseMyInfo getMyInfo(String uuid);

    ResponseLogIn login(RequestLogIn requestLogIn);

    boolean updatePassword(RequestUpdatePassword requestUpdatePassword, String uuid);

}
