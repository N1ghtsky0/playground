package jiwook.kim.playground.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdatePassword {

    private String oldPwd;
    private String newPwd;

}
