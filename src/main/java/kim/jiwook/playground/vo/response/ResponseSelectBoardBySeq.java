package kim.jiwook.playground.vo.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseSelectBoardBySeq {

    private long seq;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;

}
