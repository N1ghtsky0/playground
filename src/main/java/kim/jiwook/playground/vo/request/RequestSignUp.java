package kim.jiwook.playground.vo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestSignUp {

    @NotNull
    @Size(min = 8, max = 20)
    private String loginId;

    @NotNull
    @Size(min = 8)
    private String loginPwd;

}
