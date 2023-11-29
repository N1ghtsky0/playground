package kim.jiwook.playground.Entity;

import kim.jiwook.playground.common.BaseTimeEntity;
import kim.jiwook.playground.vo.request.RequestSignUp;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Account extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(length = 20)
    private String loginId;

    @NotNull
    private String loginPwd;

    @Column(length = 8)
    private String nickName;

    @Column(length = 36)
    private String uuid;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    public static Account from(RequestSignUp request, PasswordEncoder encoder) {
        return Account.builder()
                .loginId(request.getLoginId())
                .loginPwd(encoder.encode(request.getLoginPwd()))
                .uuid(UUID.randomUUID().toString())
                .type(AccountType.USER)
                .build();
    }

    public static Account adminFrom(RequestSignUp request, PasswordEncoder encoder) {
        return Account.builder()
                .loginId(request.getLoginId())
                .loginPwd(encoder.encode(request.getLoginPwd()))
                .uuid(UUID.randomUUID().toString())
                .type(AccountType.ADMIN)
                .build();
    }

    @Getter
    @AllArgsConstructor
    public enum AccountType {
        USER("USER"),
        ADMIN("ADMIN");

        public final String role;
    }
}
