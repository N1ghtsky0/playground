package kim.jiwook.playground.service;

import kim.jiwook.playground.vo.request.RequestLogIn;
import kim.jiwook.playground.vo.request.RequestSignUp;
import kim.jiwook.playground.vo.response.ResponseLogin;

public interface AccountService {
    boolean isLoginIdDuplicate(String loginId);
    void createAccount(RequestSignUp request);
    ResponseLogin logInProcess(RequestLogIn request);
}
