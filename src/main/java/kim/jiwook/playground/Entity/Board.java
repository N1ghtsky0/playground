package kim.jiwook.playground.Entity;

import com.sun.istack.NotNull;
import kim.jiwook.playground.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String author;

    @ManyToOne
    private Account account;

    public void setAccount(Account account) {
        this.account = account;
    }
}