package jiwook.kim.playground.Entity;

import jakarta.persistence.*;
import jiwook.kim.playground.base.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

}
