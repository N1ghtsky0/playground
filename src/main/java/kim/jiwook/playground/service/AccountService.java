package kim.jiwook.playground.service;

import kim.jiwook.playground.vo.request.RequestSignIn;

public interface AccountService {
    boolean isLoginIdDuplicate(String loginId);
    void createAccount(RequestSignIn request);
}
