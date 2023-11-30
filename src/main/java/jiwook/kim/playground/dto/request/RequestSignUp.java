package jiwook.kim.playground.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestSignUp {

    private String loginId;
    private String loginPwd;
    private String name;
    private String nickName;
    private String birthDay;

}
