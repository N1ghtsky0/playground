package kim.jiwook.playground.vo.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestInsertBoard {

    @NotNull
    private String title;

    @NotNull
    private String content;

    private String author;

}
