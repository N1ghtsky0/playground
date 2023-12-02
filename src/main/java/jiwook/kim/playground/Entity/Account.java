package jiwook.kim.playground.Entity;

import jakarta.persistence.*;
import jiwook.kim.playground.base.common.AccountType;
import jiwook.kim.playground.base.common.BaseTimeEntity;
import jiwook.kim.playground.dto.request.RequestSignUp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(nullable = false, length = 20)
    private String loginId;

    @Column(nullable = false)
    private String loginPwd;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(length = 20)
    private String nickName;

    @Column(length = 10)
    private String birthDay;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(length = 36)
    private String uuid;

    public static Account createUser(RequestSignUp requestSignUp, PasswordEncoder encoder) {
        return Account.builder()
                .loginId(requestSignUp.getLoginId())
                .loginPwd(encoder.encode(requestSignUp.getLoginPwd()))
                .name(requestSignUp.getName())
                .nickName(requestSignUp.getNickName())
                .birthDay(requestSignUp.getBirthDay())
                .type(AccountType.USER)
                .uuid(UUID.randomUUID().toString())
                .build();
    }

    public void updateLoginPwd(String newLoginPwd, PasswordEncoder encoder) {
        this.loginPwd = encoder.encode(newLoginPwd);
    }

    public static Account createAdmin(RequestSignUp requestSignUp, PasswordEncoder encoder) {
        return Account.builder()
                .loginId(requestSignUp.getLoginId())
                .loginPwd(encoder.encode(requestSignUp.getLoginPwd()))
                .name(requestSignUp.getName())
                .nickName(requestSignUp.getNickName())
                .birthDay(requestSignUp.getBirthDay())
                .type(AccountType.ADMIN)
                .uuid(UUID.randomUUID().toString())
                .build();
    }

}
