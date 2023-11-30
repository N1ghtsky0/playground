package jiwook.kim.playground.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseMyInfo {

    private String loginId;
    private String name;
    private String nickName;
    private String birthDay;
    private String createDate;

}