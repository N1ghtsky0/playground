package kim.jiwook.playground.vo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RequestInsertBoard {

    @NotEmpty(message = "제목은 빈 칸일 수 없습니다.")
    private String title;

    @NotEmpty(message = "내용은 빈 칸일 수 없습니다.")
    private String content;

    private String author;

}
