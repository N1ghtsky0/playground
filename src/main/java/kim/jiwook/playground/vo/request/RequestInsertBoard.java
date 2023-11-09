package kim.jiwook.playground.vo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestInsertBoard {

    private String title;
    private String content;
    private String author;

}
