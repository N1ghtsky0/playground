package jiwook.kim.playground.dto.response;

import jiwook.kim.playground.Entity.Board;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ResponseGetBoard {

    private Long seq;
    private String title;
    private String content;
    private String author;
    private String createDate;

    public ResponseGetBoard from(Board board) {
        this.seq = board.getSeq();
        this.author = board.getAccount().getNickName();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createDate = board.getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return this;
    }
}
