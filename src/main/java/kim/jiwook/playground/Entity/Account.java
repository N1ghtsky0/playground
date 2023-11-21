package kim.jiwook.playground.Entity;

import kim.jiwook.playground.vo.request.RequestSignIn;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String loginId;

    @NotNull
    private String loginPwd;

    private String uuid;

    public static Account from(RequestSignIn request, PasswordEncoder encoder) {
        return Account.builder()
                .loginId(request.getLoginId())
                .loginPwd(encoder.encode(request.getLoginPwd()))
                .uuid(UUID.randomUUID().toString())
                .build();
    }
}
