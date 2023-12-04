package jiwook.kim.playground.dto.response;

import jiwook.kim.playground.Entity.Account;
import lombok.Builder;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Builder
@Getter
public class ResponseMyInfo {

    private String loginId;
    private String name;
    private String nickName;
    private String birthDay;
    private String createDate;

    public ResponseMyInfo(Account entity) {
        this.loginId = entity.getLoginId();
        this.name = entity.getName();
        this.nickName = entity.getNickName();
        this.birthDay = entity.getBirthDay();
        this.createDate = entity.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}