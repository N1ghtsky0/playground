package jiwook.kim.playground.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseLogIn {

    private String accessToken;
    private String refreshToken;

}
