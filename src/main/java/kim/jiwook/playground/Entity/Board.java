package kim.jiwook.playground.Entity;

import com.sun.istack.NotNull;
import kim.jiwook.playground.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @NotNull
    @Column(length = 50)
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

}