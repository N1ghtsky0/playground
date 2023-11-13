package kim.jiwook.playground.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_BOARD("게시글을 찾을 수 없습니다.", NOT_FOUND),
    ;

    private final String message;
    private final HttpStatus httpStatus;
}
