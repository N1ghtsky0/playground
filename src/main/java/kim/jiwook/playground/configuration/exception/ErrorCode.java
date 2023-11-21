package kim.jiwook.playground.configuration.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // board
    NOT_FOUND_BOARD("게시글을 찾을 수 없습니다.", NOT_FOUND),

    // account
    DUPLICATED_LOGIN_ID("이미 가입된 로그인 아이디입니다.", BAD_REQUEST),
    ;

    private final String message;
    private final HttpStatus httpStatus;
}
