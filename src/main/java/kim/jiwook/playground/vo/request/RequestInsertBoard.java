package kim.jiwook.playground.vo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestInsertBoard {

    @NotEmpty(message = "제목은 빈 칸일 수 없습니다.")
    @Size(min = 1, max = 50)
    private String title;

    @NotEmpty(message = "내용은 빈 칸일 수 없습니다.")
    private String content;

}
