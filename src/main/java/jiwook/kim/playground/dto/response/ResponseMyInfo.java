package jiwook.kim.playground.dto.response;

import lombok.Builder;

@Builder
public class ResponseMyInfo {

    private String loginId;
    private String name;
    private String nickName;
    private String birthDay;
    private String createDate;

}